package com.xxm.zk_test
        ;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

public class ZkTest1 {
    public static void main(String[] args) {

        try {
            ZooKeeper zk = new ZooKeeper("49.234.25.12:2181", 100000, new TestWatcher());
            String node = "/my_test";
            Stat stat = zk.exists(node, false);
            //如果节点不存在，则创建节点
            if (null == stat) {
                String createResult = zk.create(node, "my_test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                //获取创建结果
                System.out.println(createResult);
            }
            //将会把“test”字符返回
            byte[] b = zk.getData(node, false, stat);
            System.out.println(new String(b));
            zk.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();


        }
    }
}

class TestWatcher implements Watcher {

    public void process(WatchedEvent arg0) {
        System.out.println("-----------------------");
        System.out.println("WatchedEvent:" + arg0);
        System.out.println("-----------------------");
    }

}
