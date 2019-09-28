package com.mybatis_plus.controller;


import com.mybatis_plus.pojo.ProcessDefinitionSelf;
import com.mybatis_plus.utils.response.CinSimpleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

@Api(value = "流程相关接口", description = "流程相关接口")
@RestController
@RequestMapping("process")
public class ProcessController {
    private static final Logger log = LoggerFactory.getLogger(ProcessController.class);

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    /**
     * 发布流程
     *
     * @param file
     * @return
     */
    @ApiOperation("发布流程")
    @PostMapping("publish")
    public CinSimpleResponse publish(@RequestParam(value = "file", required = false) MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return CinSimpleResponse.getFailResponse("上传文件有误");
        }
        try {
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            String originalFilename = file.getOriginalFilename();
            Deployment deployment = repositoryService.createDeployment()
                    .name(originalFilename)//写入部署名称
                    .addZipInputStream(zipInputStream)//流
                    .deploy();
            log.info("------>流程部署成功，id：" + deployment.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return CinSimpleResponse.getFailResponse("部署失败");
        }
        return CinSimpleResponse.getSuccessResponse("部署成功");
    }

    /**
     * 开启流程
     *
     * @param key
     * @param bussinessKey
     * @param variables
     * @return
     */
    @ApiOperation("开启流程")
    @GetMapping("start")
    //参数用类指定
    public CinSimpleResponse start(String key, String bussinessKey, @RequestParam(required = false) Map<String, Object> variables) {

        variables.remove("key");
        variables.remove("bussinessKey");
        runtimeService.startProcessInstanceByKey(key, bussinessKey, variables);

        log.info("-------------流程开启成功-------------");
        return CinSimpleResponse.getSuccessResponse("流程开启成功");
    }

    /**
     * 查看流程部署信息
     *
     * @return
     */
    @ApiOperation("查看流程部署信息")
    @GetMapping("deployMsg")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "流程定义key,不传表示查询所有"),
    })
    public CinSimpleResponse deployMsg(@RequestParam(required = false) String key) {
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        if (StringUtils.isNotEmpty(key)) {
            deploymentQuery.processDefinitionKey(key);
        }
        List<Deployment> list = deploymentQuery.orderByDeploymenTime().desc().list();
        ArrayList results = new ArrayList<>();
        for (Deployment deployment : list) {
            com.mybatis_plus.pojo.Deployment deployment1 = new com.mybatis_plus.pojo.Deployment();
            BeanUtils.copyProperties(deployment, deployment1);
            results.add(deployment1);
        }
        return CinSimpleResponse.getSuccessResponse(results);
    }

    /**
     * 查看流程定义信息
     *
     * @return
     */
    @ApiOperation("查看流程定义信息")
    @GetMapping("defMsg")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "流程定义key,不传表示查询所有"),
    })
    public CinSimpleResponse defMsg(@RequestParam(required = false) String key) {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        if (StringUtils.isNotEmpty(key)) {
            processDefinitionQuery.processDefinitionKey(key);
        }
        List<ProcessDefinition> list = processDefinitionQuery
                .orderByProcessDefinitionVersion().desc()
                .list();
        ArrayList results = new ArrayList();
        for (ProcessDefinition processDefinition : list) {
            ProcessDefinitionSelf processDefinitionSelf = new ProcessDefinitionSelf();
            BeanUtils.copyProperties(processDefinition, processDefinitionSelf);
            results.add(processDefinitionSelf);
        }
        return CinSimpleResponse.getSuccessResponse(results);
    }

    /**
     * 最新版本流程图查询
     */
    @ApiOperation("最新版本流程图查询")
    @GetMapping("queryPic")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "depId", value = "如果同一流程有多个版本，要查询指定版本的流程图需指定此参数"),
    })
    public void queryPic(@RequestParam(required = false) String depId,
                         String key, HttpServletResponse httpServletResponse) {

        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        //只要传了部署id就以部署id为准
        if (StringUtils.isNotEmpty(depId)) {
            processDefinitionQuery.deploymentId(depId);
        }
        //没传部署id就查询当前最新
        if (StringUtils.isEmpty(depId) && StringUtils.isNotEmpty(key)) {
            processDefinitionQuery.processDefinitionKey(key)
                    .latestVersion();
        }
        ProcessDefinition processDefinition = processDefinitionQuery.singleResult();
        if (null == processDefinition) {
            //流程不存在
            return;
        }

        String deploymentId = processDefinition.getDeploymentId();

        Model model = repositoryService.createModelQuery()
                .deploymentId(deploymentId).singleResult();
        if (null != model) {
            getPic(httpServletResponse, model.getId());
        } else {
            outPic(httpServletResponse, deploymentId);
        }
    }


    public void getPic(HttpServletResponse httpServletResponse, String modeId) {

        byte[] modelEditorSourceExtra = repositoryService.getModelEditorSourceExtra(modeId);

        try {
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();
            //字节数组转字节输入流
            ByteArrayInputStream is = new ByteArrayInputStream(modelEditorSourceExtra);
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = is.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            is.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 流程定义级联删除
     */
    @ApiOperation("删除流程定义信息")
    @PostMapping("delProcess")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cascade", value = "fasle:不进行级联删除;true:进行级联删除"),
    })
    public CinSimpleResponse delProcess(String deployId, Boolean cascade) {
        //如果已经开启流程 ---其他的ru表中会有关联数据删除会失败--指定true会级联删除所有有关的数据
        try {
            repositoryService.deleteDeployment(deployId, cascade);//如果为false等同于以上
        } catch (Exception e) {
            //e.printStackTrace();
            return CinSimpleResponse.getFailResponse("删除失败，此流程还有未执行完的任务");
        }
        return CinSimpleResponse.getSuccessResponse("删除成功");
    }

    /**
     * 流程实例查询
     */
    @ApiOperation("运行中的流程实例查询")
    @GetMapping("proInstanceQuery")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "流程定义key"),
            @ApiImplicitParam(name = "businessKey", value = "业务id"),
    })
    public CinSimpleResponse proInstanceQuery(String key, String businessKey) {

        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery()
                .processDefinitionKey(key)
                .processInstanceBusinessKey(businessKey)
                .orderByProcessInstanceId().desc()
                .list();

        return CinSimpleResponse.getSuccessResponse(list);

    }

    /**
     * 流程实例挂起
     */
    @ApiOperation("运行中的流程实例查询")
    @PostMapping("suspendProcessInstance")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "流程定义key"),
            @ApiImplicitParam(name = "businessKey", value = "业务id"),
    })
    public CinSimpleResponse suspendProcessInstance(String processInstanceId) {
        //先查询有没有此流程实例
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().processDefinitionId(processInstanceId).list();
        if (list == null || list.size() == 0) {
            return CinSimpleResponse.getFailResponse("此流程实例不存在");
        }
        boolean suspended = list.get(0).isSuspended();
        if (suspended) {
            return CinSimpleResponse.getFailResponse("该流程实例已挂起，请勿重复操作");
        }
        runtimeService.suspendProcessInstanceById(processInstanceId);

        return CinSimpleResponse.getSuccessResponse("流程实例挂起成功");
    }


    /**
     * 流程实例的激活
     */
    @ApiOperation("流程实例的激活")
    @PostMapping("activiteProcessInstance")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "流程定义key"),
            @ApiImplicitParam(name = "businessKey", value = "业务id"),
    })
    public CinSimpleResponse activiteProcessInstance(String processInstanceId) {
        //先查询有没有此流程实例
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().processDefinitionId(processInstanceId).list();
        if (list == null || list.size() == 0) {
            return CinSimpleResponse.getFailResponse("此流程实例不存在");
        }
        boolean suspended = list.get(0).isSuspended();
        if (!suspended) {
            return CinSimpleResponse.getFailResponse("该流程实例已激活，请勿重复操作");
        }
        runtimeService.activateProcessInstanceById(processInstanceId);

        return CinSimpleResponse.getSuccessResponse("该流程实例激活成功");
    }


    private void outPic(HttpServletResponse httpServletResponse, String deploymentId) {
        List<String> deploymentResourceNames = repositoryService.getDeploymentResourceNames(deploymentId);

        String picName = null;
        for (String name : deploymentResourceNames) {
            if (name.indexOf(".png") >= 0) {
                picName = name;
                break;
            }
        }

        InputStream resourceAsStream = repositoryService.getResourceAsStream(deploymentId, picName);

        try {
            BufferedImage read = ImageIO.read(resourceAsStream);
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();
            ImageIO.write(read, "png", outputStream);
            //    关闭流
            resourceAsStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
