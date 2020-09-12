package com.xxm.zk_test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ZkTest2 {

    private static final String connectString = "49.234.25.12:2181";

    private static final int sessionTimeout = 2000;

    ZooKeeper zkClient = null;

    @Before
    public void init() throws Exception {
        zkClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                //收到事件通知后的回调函数（应该是我们自己的事件处理逻辑）
                System.out.println(event.getType() + "----" + event.getPath() + ">>>>" + event);//创建删除节点会感知到
                try {
                    zkClient.getChildren("/", true);//再次触发监听  监听/
                } catch (Exception e) {
                }
            }
        });
    }

    /**
     * 创建数据节点到zk中
     * 描述:在zk的根路径下创建一个节点idea_mac_test，内容为hellozk，
     * 第三个参数是权限，我们给的是Ids.OPEN_ACL_UNSAFE 意思是 This is a completely open ACL .
     */
    @Test
    public void testCreate() throws Exception {
        Stat exists = zkClient.exists("/idea_mac_test", true);
        if (exists == null) {
            String create = zkClient.create("/idea_mac_test", "hellozk".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
            System.out.println("创建----" + create);
        }

        System.out.println(exists);

    }
    /**
     /**
     * 获取节点内容
     * 描述：获取/eclipse这个节点的内容
     * 第二个参数：是否监听，这里的意思：/eclipse节点下内容发生变化时，则会通知。
     * @throws Exception
     */
    @Test
    public void testGetZnode() throws Exception {
        byte[] data = zkClient.getData("/idea_mac_test", false, null);
        System.out.println(new String(data, "utf-8"));
    }

    /**
     * 设置znode内容
     * 修改/eclipse节点的内容为helloWHB
     * @throws Exception
     */
    @Test
    public void testSetData() throws Exception {
        zkClient.setData("/idea_mac_test", "helloWHB".getBytes(), -1);
        byte[] data = zkClient.getData("/idea_mac_test", false, null);
        System.out.println(new String(data, "utf-8"));
    }

    /**
     * 删除znode
     * 删除/eclipse这个节点
     * @throws Exception
     */
    @Test
    public void testDelete() throws Exception {
        // -1表示所有版本
        zkClient.delete("/idea_mac_test", -1);
        Stat exists = zkClient.exists("/idea_mac_test", true);
        System.out.println(exists==null?"不存在":"存在");

    }
    /**获取子节点
     * @throwsException
     */
    @Test
    public void getChildren() throws Exception {
        List<String> children = zkClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }
        Thread.sleep(Long.MAX_VALUE);//让程序一直运行，在CRT终端里 ls / watch ;create /appe www ；观察控制台打印情况
    }



}
