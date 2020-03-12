package com.dccj.controller;

import com.alibaba.fastjson.JSON;
import com.dccj.entity.*;
import com.dccj.exception.AppException;
import com.dccj.extend.BaiduAipOcr;
import com.dccj.service.*;
import com.dccj.uitl.Validation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Hash;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @Value("#{prop.uploadFilePathSub}")
    private String uploadFilePathSub;
    @Value("#{prop.uploadServerUrl}")
    private String serverUrl;
    @Resource
    private DataDirService dataDirService;
    @Resource
    private CarOrderService carOrderService;
    @Resource
    private SysSmsService sysSmsService;

    @RequestMapping("/addCars")
    @ResponseBody
    public RespEntity addCars(CarCenter carCenter, @RequestParam String code) {
        RespEntity respEntity = new RespEntity();
        if (StringUtils.isEmpty(carCenter.getUsername()))
            throw new AppException("用户姓名参数错误");
        if (StringUtils.isEmpty(carCenter.getMobile()))
            throw new AppException("手机号参数缺失");
        if (!Validation.isMobile(carCenter.getMobile()))
            throw new AppException("手机号参数错误");
        if (StringUtils.isEmpty(code))
            throw new AppException("验证码参数缺失");
        SysSms sysSms = sysSmsService.selectByPhone(carCenter.getMobile());
        if (!sysSms.getCode().equals(code))
            throw new AppException("验证码错误");
        if (System.currentTimeMillis() / 1000 - Long.parseLong(sysSms.getTime()) > 15 * 60)
            throw new AppException("验证码已失效");
        if (StringUtils.isEmpty(carCenter.getCarNum()))
            throw new AppException("车牌号参数缺失");
        if (StringUtils.isEmpty(carCenter.getCarIdNum()))
            throw new AppException("车辆识别代号参数缺失");
        if (StringUtils.isEmpty(carCenter.getCarPeople()))
            throw new AppException("车辆所有人参数缺失");
        if (StringUtils.isEmpty(carCenter.getCarDriveNum()))
            throw new AppException("车辆发动机号码参数缺失");
        if (StringUtils.isEmpty(carCenter.getCarType()))
            throw new AppException("车辆类型参数缺失");
        if (StringUtils.isEmpty(carCenter.getCarSize()))
            throw new AppException("车辆外廓尺寸参数缺失");
        if (StringUtils.isEmpty(carCenter.getCarFuel()))
            throw new AppException("燃油类型参数缺失");
        List<DataDir> carFuelTypeList = dataDirService.selectByType("carFuelType");
        if (!carFuelTypeList.stream().filter(t -> t.getValue().equals(carCenter.getCarFuel())).findAny().isPresent())
            throw new AppException("燃油类型参数错误");
        if (StringUtils.isEmpty(carCenter.getCarImgFront()))
            throw new AppException("车辆行驶证正面参数缺失");
        if (StringUtils.isEmpty(carCenter.getCarImgBack()))
            throw new AppException("车辆行驶证背面参数缺失");
        if (StringUtils.isEmpty(carCenter.getCarAtType()))
            throw new AppException("车辆变速箱类型参数缺失");
        List<DataDir> carAtTypeList = dataDirService.selectByType("carAtType");
        if (!carAtTypeList.stream().filter(t -> t.getValue().equals(carCenter.getCarAtType())).findAny().isPresent())
            throw new AppException("车辆变速箱类型参数错误");
        if (StringUtils.isEmpty(carCenter.getCarDriveType()))
            throw new AppException("车辆驱动形式参数缺失");
        List<DataDir> carDriverTypeList = dataDirService.selectByType("carDriverType");
        if (!carDriverTypeList.stream().filter(t -> t.getValue().equals(carCenter.getCarDriveType())).findAny().isPresent())
            throw new AppException("车辆驱动形式参数错误");
        if (StringUtils.isEmpty(carCenter.getCarColor()))
            throw new AppException("车辆车身颜色参数缺失");
        List<DataDir> carColorTypeList = dataDirService.selectByType("carColorType");
        if (!carColorTypeList.stream().filter(t -> t.getValue().equals(carCenter.getCarColor())).findAny().isPresent())
            throw new AppException("车辆车身颜色参数错误");
        if (StringUtils.isEmpty(carCenter.getCarOut()))
            throw new AppException("车辆排量参数缺失");
        List<DataDir> carOutTypeList = dataDirService.selectByType("carOutType");
        if (!carOutTypeList.stream().filter(t -> t.getValue().equals(carCenter.getCarOut())).findAny().isPresent())
            throw new AppException("车辆排量参数错误");
        carCenterService.insertSelective(carCenter);
        respEntity.setData(carCenter);
        return respEntity;
    }

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
            throw new AppException("id参数错误");
        carCenterService.updateByPrimaryKeySelective(carCenter);
        respEntity.setData(carCenter);
        return respEntity;
    }

    @RequestMapping("/delCars")
    @ResponseBody
    public RespEntity delCars(@RequestParam Integer id) {
        List<CarOrder> list = carOrderService.selectByCarId(id);
        if (list.size() > 0)
            throw new AppException("该车辆已经提交过车检数据，无法删除");
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
    @CrossOrigin(origins = "*")
    public RespEntity readCarInfo(MultipartFile file, @RequestParam String type,
                                  HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(type))
            throw new AppException("参数错误");
        String filePath = "", fileUrl = "", path = "carImg";
        String uploadFilePath = uploadFilePathSub + path;
        CarCenter carCenter = new CarCenter();
        try {
            log.info("===================开始文件上传=====================");
            if (!file.isEmpty()) {
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                log.info("文件名: " + file.getOriginalFilename() + "  文件长度: " + file.getSize() / 1024 + "K");
                String fileName = file.getOriginalFilename();
                String newFileName = fileName.substring(0, fileName.lastIndexOf("."));
                String extendsFileName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                newFileName = newFileName + uuid + extendsFileName;
                // 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(uploadFilePath, newFileName));
                filePath = uploadFilePath + "/" + newFileName;
                fileUrl = serverUrl + path + "/" + newFileName;
                log.info("==================上传文件结束======================");
                log.info("==================开始识别行驶证内容======================");
                HashMap<String, String> param = new HashMap<>();
                param.put("vehicle_license_side", type);
                param.put("detect_direction", "true");
                param.put("accuracy", "high");
                JSONObject ret = BaiduAipOcr.readCarImgByBaidu(filePath, param);
                JSONObject words = ret.getJSONObject("words_result");
                List<DataDir> list = dataDirService.selectByType("carType");
                for (String key : words.keySet()) {
                    if (key.equals("品牌型号")) {
                        carCenter.setCarBrandNum(words.getJSONObject(key).getString("words"));
                    } else if (key.equals("发证日期")) {
                        carCenter.setCarBeginDate(words.getJSONObject(key).getString("words"));
                    } else if (key.equals("使用性质")) {
                        carCenter.setCarUse(words.getJSONObject(key).getString("words"));
                    } else if (key.equals("发动机号码")) {
                        carCenter.setCarDriveNum(words.getJSONObject(key).getString("words"));
                    } else if (key.equals("号牌号码")) {
                        carCenter.setCarNum(words.getJSONObject(key).getString("words"));
                    } else if (key.equals("所有人")) {
                        carCenter.setCarPeople(words.getJSONObject(key).getString("words"));
                    } else if (key.equals("住址")) {
                        carCenter.setCarAddress(words.getJSONObject(key).getString("words"));
                    } else if (key.equals("注册日期")) {
                        carCenter.setCarRegNum(words.getJSONObject(key).getString("words"));
                    } else if (key.equals("车辆识别代号")) {
                        carCenter.setCarIdNum(words.getJSONObject(key).getString("words"));
                    } else if (key.equals("车辆类型")) {
                        carCenter.setCarType(words.getJSONObject(key).getString("words"));
                        boolean isSuccess = false;
                        for (DataDir dataDir : list) {
                            if (dataDir.getName().equals(carCenter.getCarType()))
                                isSuccess = true;
                        }
                        if (!isSuccess)
                            throw new AppException("行驶证中的车辆类型识别错误,请从新上传清晰照片");
                    } else if (key.equals("整备质量")) {
                        carCenter.setCarWeight(words.getJSONObject(key).getString("words"));
                    } else if (key.equals("准牵引总质量")) {
                        carCenter.setCarTowWeight(words.getJSONObject(key).getString("words"));
                    } else if (key.equals("核定载人数")) {
                        carCenter.setCarLoadPeople(words.getJSONObject(key).getString("words"));
                    } else if (key.equals("外廓尺寸")) {
                        carCenter.setCarSize(words.getJSONObject(key).getString("words"));
                        if (carCenter.getCarSize().split("X").length != 3)
                            throw new AppException("行驶证中的外廓尺寸识别错误,请从新上传清晰照片");
                    } else if (key.equals("总质量")) {
                        carCenter.setCarTotalWeight(words.getJSONObject(key).getString("words"));
                    } else if (key.equals("档案编号")) {
                        carCenter.setCarRecordNum(words.getJSONObject(key).getString("words"));
                    } else if (key.equals("检验记录")) {
                        carCenter.setCarCheckLog(words.getJSONObject(key).getString("words"));
                    } else if (key.equals("燃油类型")) {
                        carCenter.setCarFuel(words.getJSONObject(key).getString("words"));
                    } else if (key.equals("核定载质量")) {
                        carCenter.setCarLoadApproved(words.getJSONObject(key).getString("words"));
                    } else if (key.equals("备注")) {
                        carCenter.setCarRemark(words.getJSONObject(key).getString("words"));
                    }
                }
                log.info("==================识别行驶证内容完毕======================");
            }
        } catch (Exception e) {
            throw new AppException("上传文件失败", e);
        }
        RespEntity respEntity = new RespEntity();
        Map<String, Object> map = new HashMap<>();
        map.put("carInfo", carCenter);
        map.put("urlData", fileUrl);
        map.put("pathData", filePath);
        respEntity.setData(map);
        response.setHeader("Access-Control-Allow-Origin", "*");
        return respEntity;
    }
}
