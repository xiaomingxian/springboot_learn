package com.mybatis_plus.controller;

import com.mybatis_plus.utils.response.CinSimpleResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "任务相关接口", description = "任务相关接口")
@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @ApiOperation("查询代办任务")
    @GetMapping("queryTaskByUser")
    public CinSimpleResponse queryTaskByUser(String userName, @RequestParam(required = false) String page, @RequestParam(required = false) String limit) {
        TaskQuery query = taskService.createTaskQuery()
                .taskCandidateOrAssigned(userName)
                .orderByTaskCreateTime().desc();

        List<Task> list = null;
        if (!StringUtils.isEmpty(page) && !StringUtils.isEmpty(limit)) {
            list = query.listPage(Integer.parseInt(page), Integer.parseInt(limit));
        } else {
            list = query.list();
        }

        System.out.println("----------------- 代办任务 --------------------");
        System.out.println(list);
        return CinSimpleResponse.getSuccessResponse(list);
    }


    /**
     * 任务详情查询--输出连线与批注信息查询
     *
     * @return
     */
    @ApiOperation("拾取任务")
    @GetMapping("pickUpTask")
    public CinSimpleResponse pickUpTask() {


        return CinSimpleResponse.getSuccessResponse("");
    }

    /**
     * 组任务拾取回退
     *
     * @return
     */
    @ApiOperation("组任务拾取回退")
    @GetMapping("rollBackGroupTask")
    public CinSimpleResponse rollBackGroupTask() {
        //判断当前节点类型-如果是
        return CinSimpleResponse.getSuccessResponse("");
    }

    /**
     * 办理任务
     * 如果是组任务，自动拾取后办理
     */
    @ApiOperation("办理任务")
    @GetMapping("doTask")
    public CinSimpleResponse doTask() {


        return CinSimpleResponse.getSuccessResponse("任务办理成功");
    }

    /**
     * 个人任务回退--是否可行
     *
     * @return
     */
    @ApiOperation("个人任务回退")
    @GetMapping("rollbackPerSonTask")
    public CinSimpleResponse rollbackPerSonTask() {
        //判断当前节点类型-如果是
        return CinSimpleResponse.getSuccessResponse("");
    }

    @ApiOperation("判断当前节点节点类型")
    @GetMapping("judgeType")
    public CinSimpleResponse judgeType() {
        return CinSimpleResponse.getSuccessResponse("");
    }


    /**
     * 活动节点定位查询
     */


}
