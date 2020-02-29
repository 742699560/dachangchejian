package com.dccj.controller;

import com.alibaba.fastjson.JSON;
import com.dccj.entity.CarCenter;
import com.dccj.entity.CompanyEntity;
import com.dccj.entity.UserCar;
import com.dccj.exception.AppException;
import com.dccj.extend.BaiduAipOcr;
import com.dccj.service.CarCenterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.shiro.crypto.hash.Hash;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/cars")
@Slf4j
public class CarCenterController extends BaseController {

    @Resource
    private CarCenterService carCenterService;
    @Value("#{prop.uploadFilePath}")
    private String uploadFilePathSub;
    @Value("#{prop.serverUrl}")
    private String serverUrl;

    @RequestMapping("/queryCars")
    @ResponseBody
    public RespEntity queryCars(@RequestParam Integer userId) {
        List<CarCenter> list = carCenterService.selectAllByUserId(userId);
        RespEntity respEntity = new RespEntity();
        respEntity.putListData(list);
        return respEntity;
    }

    @RequestMapping("/updateCars")
    @ResponseBody
    public RespEntity updateCars(CarCenter carCenter) {
        RespEntity respEntity = new RespEntity();
        if (carCenter.getId() == null)
            throw new AppException("参数错误");
        carCenterService.updateByPrimaryKeySelective(carCenter);
        respEntity.setData(carCenter);
        return respEntity;
    }

    @RequestMapping("/delCars")
    @ResponseBody
    public RespEntity delCars(@RequestParam Integer id) {
        carCenterService.deleteByPrimaryKey(id);
        RespEntity respEntity = new RespEntity();
        return respEntity;
    }

    /**
     * 行驶证正反面文件上传
     * 单个文件上传 可通过formData异步上传
     */
    @RequestMapping(value = "/readCarInfo")
    @ResponseBody
    public RespEntity readCarInfo(MultipartFile file, @RequestParam String type,
                                          HttpServletRequest request) {
        String filePath = "", fileUrl = "", path = "carImg";
        String uploadFilePath = uploadFilePathSub + "\\" + path;
        JSONObject ret = new JSONObject();
        try {
            log.info("===================开始文件上传=====================");
            if (!file.isEmpty()) {
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                log.info("文件名: " + file.getOriginalFilename() + "  文件长度: " + file.getSize() / 1024 / 1024);
                String fileName = file.getOriginalFilename();
                String newFileName = fileName.substring(0, fileName.lastIndexOf("."));
                String extendsFileName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                newFileName = newFileName + uuid + extendsFileName;
                // 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(uploadFilePath, newFileName));
                filePath = uploadFilePath + "\\" + newFileName;
                fileUrl = serverUrl + "/upload/" + path + "/" + newFileName;
                log.info("==================上传文件结束======================");
                log.info("==================开始识别行驶证内容======================");
                HashMap<String, String> param = new HashMap<>();
                param.put("vehicle_license_side", type);
                param.put("detect_direction", "true");
                ret = BaiduAipOcr.readCarImgByBaidu(filePath, param);
                log.info("==================识别行驶证内容完毕======================");
            }
        } catch (Exception e) {
            throw new AppException("上传文件失败", e);
        }
        RespEntity respEntity = new RespEntity();
        Map<String, Object> map = new HashMap<>();
        map.put("carInfo", ret);
        map.put("urlData", fileUrl);
        map.put("pathData", filePath);
        respEntity.setData(map);
        return respEntity;
    }
}
