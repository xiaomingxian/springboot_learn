package com.mybatis_plus.controller;

import com.mybatis_plus.service.ModelerService;
import io.micrometer.core.instrument.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "模型", description = "工作流模型在线编辑")
@Controller
@RequestMapping("/modeler")
public class ModelerController {


    @Resource
    private ModelerService modelerService;

    /**
     * 创建流程模型
     *
     * @param name
     * @param key
     * @param description
     * @param request
     * @param response
     * @return
     */
    @ApiOperation("创建流程模型")
    @PostMapping(value = "/create")
    public String createModel(@RequestParam("name") String name, @RequestParam("key") String key,
                              @RequestParam("description") String description,
                              HttpServletRequest request, HttpServletResponse response) {
        try {
            //创建空模型
            String modelId = modelerService.crateModel(name, key, description);
            if (StringUtils.isBlank(modelId)) {
                throw new RuntimeException("创建modeler失败modelId:" + modelId);
            }

            return "redirect:../modeler.html?modelId=" + modelId;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 模型列表
     *
     * @param modelAndView
     * @return
     */
    @ApiOperation("模型列表")
    @GetMapping("/model/list")
    public ModelAndView modelList(ModelAndView modelAndView) {
        modelerService.queryModelList();
        return modelAndView;
    }
}