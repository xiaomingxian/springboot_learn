package com.xxm.zk_test;

import com.sun.xml.internal.fastinfoset.tools.FI_DOM_Or_XML_DOM_SAX_SAXEvent;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZkTest3_ServerDynamicUpAndDown {
    private String connectString = "49.234.25.12:2181";
    private int sessionTimeOut = 2000;
    private ZooKeeper zkClient = null;
    private String parentNode = "/servers";




    public static void main(String[] args) throws IOException {
        System.out.println("1111\r\n2222");
    }


    //1.服务上线,注册到zk
    @Test
    public void serverRun() {

        try {
            ZkTest3_ServerDynamicUpAndDown server = new ZkTest3_ServerDynamicUpAndDown();
            server.getConnectServer();
            //创建服务跟节点

            // 2.利用zk连接注册服务器信息
            String[] hosts = {"www.baidu.com", "www.alibaba.com", "www.sina.com"};
            List<String> successServers = server.registServer(hosts);
            // 3.启动业务功能
            server.business(successServers);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //客户端监听
    @Test
    public void clientWatch() {
        try {

            //1.获取zk连接
            ZkTest3_ServerDynamicUpAndDown client = new ZkTest3_ServerDynamicUpAndDown();
            client.getConnectClient();
            //2.获取servers的子节点信息，从中获取服务器信息列表
            client.getServerList();
            //3.业务进程启动
            client.businessClient();


        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    //****************************************************** REGISTER SERVERS START****************************************************


    /**
     * 连接zk
     *
     * @throws IOException
     */
    public void getConnectServer() throws IOException {
        zkClient = new ZooKeeper(connectString, sessionTimeOut, new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                try {
                    System.out.printf("------>>>>>>%s\n", "监听到一些变化,继续监听");
                    if (zkClient.exists("/servers", true) != null) {

                        zkClient.getChildren("/servers", true);//再次触发监听  监听/
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });
    }

    /**
     * 注册服务器
     */
    public List<String> registServer(String[] hostnames) throws Exception {

        Stat existsParent = zkClient.exists(parentNode, true);
        if (existsParent == null) {
            zkClient.create(parentNode, "服务跟节点".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
        System.out.println(">>>>>跟节点是否存在:" + zkClient.exists(parentNode, true));
        List successServers = new ArrayList();
        for (String hostname : hostnames) {

            Stat existsSon = zkClient.exists(parentNode + "/server", true);
            try {
                //临时且顺序的节点
                String path = zkClient.create(parentNode + "/server", hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
                //String path = zkClient.create(parentNode , hostname.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
                System.out.println(hostname + "----->服务上线了:" + path);
                successServers.add(hostname);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return successServers;

    }

    /**
     * 业务功能
     *
     * @param hostname
     * @throws InterruptedException
     */
    public void business(List<String> hostname) throws InterruptedException {

        System.out.println(hostname + " 服务正在运行 ……");
        Thread.sleep(Long.MAX_VALUE);
    }

    //****************************************************** REGISTER SERVERS END ****************************************************
    //****************************************************** CLIENT WATCHER START ****************************************************
    private void getServerList() throws KeeperException, InterruptedException {
        //1.获取服务器子节点信息，并且对父节点进行监听
        List<String> children = zkClient.getChildren(parentNode, true);
        //2.存储服务器信息列表
        List<String> servers = new ArrayList();
        //3.遍历所有节点，获取节点中的主机名称信息
        for (String child : children) {
            byte[] data = zkClient.getData(parentNode + "/" + child, false, null);
            servers.add(new String(data));
        }
        //4.打印服务器列表信息
        System.out.println(servers);
    }

    private void getConnectClient() throws IOException {
        //创建到zk的客户端连接
        zkClient = new ZooKeeper(connectString, sessionTimeOut, new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                try {

                    System.out.print("监听到服务列表发生变化::");
                    getServerList();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    private void businessClient() throws InterruptedException {
        System.out.println("client is working ……");
        Thread.sleep(Long.MAX_VALUE);
    }
    //****************************************************** CLIENT WATCHER END   ****************************************************

}
