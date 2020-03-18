package com.dccj.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.dccj.exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class FileUploadController {

    @Value("#{prop.uploadFilePathSub}")
    private String uploadFilePathSub;
    @Value("#{prop.uploadServerUrl}")
    private String serverUrl;

    /**
     * 上传多个文件时，前台表单中文件控件的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件
     * 只能进行form表单的同步上传
     *
     * @param myfiles
     * @param request
     * @return
     */
    @RequestMapping(value = "/app/common/fileUpload")
    public Map<String, Object> fileUpload(@RequestParam String path, @RequestParam MultipartFile[] myfiles,
                                          HttpServletRequest request) {
        List<String> paths = new ArrayList<String>();
        List<String> localPaths = new ArrayList<String>();
        String uploadFilePath = uploadFilePathSub + "\\" + path;
        try {
            System.out.println("===================开始文件上传=====================");
            for (MultipartFile myfile : myfiles) {
                if (myfile.isEmpty())
                    continue;
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                System.out.println("文件名: " + myfile.getOriginalFilename() + "  文件长度: " + myfile.getSize());
                // 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                String fileName = myfile.getOriginalFilename();
                String newFileName = fileName.substring(0, fileName.lastIndexOf("."));
                String extendsFileName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                newFileName = newFileName + uuid + extendsFileName;
                FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(uploadFilePath, newFileName));
                localPaths.add(uploadFilePath + "\\" + newFileName);
                String serverUrl = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 10));
                paths.add(serverUrl + "/upload/" + path + "/" + newFileName);
            }
            System.out.println("==================上传文件结束======================");
        } catch (Exception e) {
            throw new AppException("上传文件失败");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", 1);
        map.put("urlData", paths);
        map.put("pathData", localPaths);
        map.put("message", "上传成功!");
        return map;
    }


    /**
     * 单个文件上传 可通过formData异步上传
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/app/common/sigleFileUpload")
    @ResponseBody
    public RespEntity fileUpload(@RequestParam String path, MultipartFile file,
                                 HttpServletRequest request) {
        String filePath = "", fileUrl = "";
        String uploadFilePath = uploadFilePathSub + path;
        try {
            log.info("===================开始文件上传=====================");
            if (!file.isEmpty()) {
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                log.info("文件名: " + file.getOriginalFilename() + "  文件长度: " + file.getSize());
                // 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                String fileName = file.getOriginalFilename();
                String newFileName = fileName.substring(0, fileName.lastIndexOf("."));
                String extendsFileName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
                newFileName = newFileName + uuid + extendsFileName;
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File(uploadFilePath, newFileName));
                filePath = uploadFilePath + "/" + newFileName;
                fileUrl = serverUrl + path + "/" + newFileName;
            }
            log.info("==================上传文件结束======================");
        } catch (Exception e) {
            throw new AppException("上传文件失败", e);
        }
        RespEntity respEntity = new RespEntity();
        Map<String, Object> map = new HashMap<>();
        map.put("urlData", fileUrl);
        map.put("pathData", filePath);
        respEntity.setData(map);
        return respEntity;
    }
}
