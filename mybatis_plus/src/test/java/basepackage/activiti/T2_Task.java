package basepackage.activiti;

import com.mybatis_plus.application.MyBatisPlusApplication;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.IdentityLinkType;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = MyBatisPlusApplication.class)
@RunWith(SpringRunner.class)
public class T2_Task {

    @Autowired
    private TaskService taskService;

    /**
     * 判断流程是否挂起
     */
    @Test
    public void isSuspended() {
        String userName = "tom";
        List<Task> list = taskService.createTaskQuery()
                //.taskAssignee(userName)
                .taskCandidateOrAssigned("新用户")
                //.taskOwner("新用户")
                .list();
        System.out.println(list);
        //boolean suspended = list.get(0).isSuspended();

        //taskService.complete("10008");
        //if (!suspended) {
        //    taskService.complete(list.get(0).getId());
        //}
        //
        //System.out.println("---------任务办理完成");

    }

    /**
     * 组任务查询
     */
    @Test
    public void groupUserTaskQuery() {
        List<Task> list = taskService.createTaskQuery().taskCandidateUser("Jerry").list();
        System.out.println(list);
    }

    /**
     * 组任务添加办理人
     */
    @Test
    public void addUser() {
        String taskId = "10017";


        String newUser = "个人任务新用户";
        //taskService.addCandidateUser(taskId, newUser);
        taskService.addUserIdentityLink(taskId, "新用户", IdentityLinkType.ASSIGNEE);//唯一，添加会替换
        //taskService.addUserIdentityLink(taskId, "新用户", IdentityLinkType.OWNER);//唯一，添加会替换
        System.out.println("------>添加决策组成员成功");


    }

}
