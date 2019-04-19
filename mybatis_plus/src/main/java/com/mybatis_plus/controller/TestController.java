package com.mybatis_plus.controller;

import com.mybatis_plus.core.aop.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Api(value = "测试controller", description = "测试controller")
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private RepositoryService repositoryService;

    @GetMapping("void")
    @ApiOperation("log测试返回值为空")
    @Log(function_id = "0", operate_type = "log测试返回值为空", operate_content = "log测试返回值为空")
    public void voidTest() {
        System.out.println("--------->voidTest");
    }

    @GetMapping("exTest")
    @Log(function_id = "0", operate_type = "log异常测试", operate_content = "log异常测试")
    public void exTest() throws Exception {
        throw new Exception("我的异常");
    }

}
