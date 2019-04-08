package com.mybatis_plus.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipInputStream;

@Api("工作流相关接口")
@RestController
@RequestMapping("workflow")
public class WorkFlowController {


    @Autowired
    private RepositoryService repositoryService;

    @ApiOperation("发布流程")
    @PostMapping("publish")
    public Map publish(MultipartFile multipartFile) {
        HashMap<Object, Object> map = new HashMap<>();
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        String originalFilename = multipartFile.getOriginalFilename();
        Deployment deployment = repositoryService.createDeployment()
                .name(originalFilename)//写入部署名称
                .addZipInputStream(zipInputStream)//流
                .deploy();
        System.out.println("---->流程部署成功，id：" + deployment.getId());
        map.put("status", "部署成功");
        return map;
    }

    @ApiOperation("开启流程")
    @PostMapping("start")
    public Map start(String key, String bussinessKey) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("status", "流程开启成功");
        return map;
    }
    
}
