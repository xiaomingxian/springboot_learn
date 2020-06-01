package java.t3_分布式通信框架.code;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class T1_NIO {

    private int port = 8080;
    //轮询器   大堂经理
    private Selector selector;

    //缓冲区  等候区
    private ByteBuffer buffer = ByteBuffer.allocate(1024);


    public static void main(String[] args) {

    }


    //大堂经理 初始化,准备好营业
    public T1_NIO(int port) {
        this.port = port;
        try {

            ServerSocketChannel jingLi = ServerSocketChannel.open();//经理
            jingLi.bind(new InetSocketAddress(this.port));//默认你本地ip
            jingLi.configureBlocking(false);//默认时阻塞 为了兼容BIO

            this.selector = Selector.open();//营业

            jingLi.register(selector, SelectionKey.OP_ACCEPT);//已经准备好哦轮询了

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //非阻塞体现
    public void  listen(){



    }

    //do
}
