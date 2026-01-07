package com.hjy.controller;

import com.hjy.pojo.Result;
import com.hjy.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
    @PostMapping("/upload")
    public Result upload (MultipartFile file) throws Exception {


        log.info("文件上传，{}", file.getOriginalFilename());

        /**
         * 本地存储不推荐
         */
        /*
        String originalFileName = file.getOriginalFilename();
        String backName = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString()+backName;
        // 本地路径存储
        file.transferTo(new File(" " ));
        return Result.success();
        */
        String url = aliyunOSSOperator.upload(file, file.getOriginalFilename());
        log.info("文件上传OSS到:{}",url);

        // 当点击图片时，立马保存在OSS 服务端返回url地址，点击上传时，前端将url赋给image属性，在请求体中发送给服务端

        return  Result.success(url);
    }
}
