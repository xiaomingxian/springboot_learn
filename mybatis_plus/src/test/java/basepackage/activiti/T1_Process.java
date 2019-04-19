package basepackage.activiti;

import com.mybatis_plus.application.MyBatisPlusApplication;
import com.mybatis_plus.pojo.User;
import com.mybatis_plus.service.UserService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipInputStream;

@SpringBootTest(classes = MyBatisPlusApplication.class)
@RunWith(SpringRunner.class)
public class T1_Process {

    @Autowired
    private UserService userService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;


    @Test
    public void t1() {
        User user = userService.selectById(1);
        List<Deployment> list = repositoryService.createDeploymentQuery().list();
        System.out.println("-------------数据源1测试----->" + user.getUsername());
        System.out.println("------activiti数据源测试----->" + list + " size:" + list.size());
    }

    /**
     * 部署流程
     */
    @Test
    public void publish() {
        repositoryService.createDeployment()
                .addClasspathResource("processes/test1.bpmn")
                .addClasspathResource("processes/test1.png")
                .deploy();
    }

    /**
     * 流方式部署
     */
    @Test
    public void zipStreamPublish() {

        InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream("processes/T1.zip");
        ZipInputStream zipInputStream = new ZipInputStream(systemResourceAsStream);
        Deployment deployment = repositoryService.createDeployment()
                .name("zip形式部署")
                .addZipInputStream(zipInputStream)
                .deploy();

        System.out.println("--->流程部署成功：" + deployment.getId() + " name:" + deployment.getName());
    }

    /**
     * 查看流程部署信息
     */
    @Test
    public void show() {
        String key = "myProcess_1";
        String depId = "1";

        List<Deployment> list = repositoryService.createDeploymentQuery()
                .deploymentKey(key)
                //.deploymentId(depId)
                .list();
        System.out.println(list);
    }

    /**
     * 删除流程部署信息
     */
    @Test
    public void delDefination() {
        //部署id可根据部署key获取【间接】
        String deployId = "15017";
        //如果已经开启流程 ---其他的ru表中会有关联数据删除会失败--指定true会级联删除所有有关的数据
        repositoryService.deleteDeployment(deployId, false);//是否级联删除
        //级联删除api没了？？？
        System.out.println("删除成功");
    }


    /**
     * 查看流程定义信息
     */
    @Test
    public void showDefination() {

        String definationKey = "process";
        String depId = "1";
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(definationKey)
                //.deploymentId(depId)
                //.latestVersion()//最新版本
                .list();
                //.listPage(0, 3);
        System.out.println("------->流程定义信息" + processDefinitions);
    }

    /**
     * 流程图
     */
    @Test
    public void picQuery() {
        String definationKey = "T1";
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey(definationKey)
                //.deploymentId(depId)
                .latestVersion()//最新版本
                .singleResult();
        InputStream processModel = repositoryService.getProcessModel(processDefinition.getId());


        File file = new File("D:/T1.png");

        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            //记录读取结果的长度
            int len = 0;
            //每次读取的长度
            byte[] b = new byte[1024];
            while ((len = processModel.read(b)) != -1) {
                bufferedOutputStream.write(b, 0, len);
                bufferedOutputStream.flush();
            }
            bufferedOutputStream.close();
            processModel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 流程实例查询
     */
    @Test
    public void proInstanceQuery() {

        String key = "T1";
        String businessKey = "2";

        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery()
                .processDefinitionKey(key)
                .processInstanceBusinessKey(businessKey)
                .list();

        System.out.println(list);

    }

    /**
     * 流程实例挂起
     */
    @Test
    public void guaQi() {
        //ru_task表中的挂起字段状态为2表示挂起
        String processInstanceId = "10001";

        runtimeService.suspendProcessInstanceById(processInstanceId);
        System.out.println("---------流程实例挂起成功");
    }

    /**
     * 激活流程实例
     */
    @Test
    public void activiteProcessInstance() {
        String processInstanceId = "10001";
        runtimeService.activateProcessInstanceById(processInstanceId);
        System.out.println("---------流程实例激活成功");
    }

    @Test
    public void timeTest() {
        Calendar c = Calendar.getInstance();
        // 过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);

        System.out.println(c.get(Calendar.MONTH));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(c.getTime());
        System.out.println(format);
        Date m = c.getTime();

        System.out.println("_______-----");
        Date date = new Date(1531756800000L);
        String format1 = simpleDateFormat.format(date);
        System.out.println(format1);


        System.out.println(new Date().getTime());
    }



}
