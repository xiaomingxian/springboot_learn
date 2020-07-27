package xxm.controller;

import com.rabbitmq.client.*;
import org.junit.Test;
import xxm.utils.ConnectUtil;

import java.io.IOException;

public class TestMq {

    private static final String QUEUE_NAME = "queue1";

    @Test
    public void sendMsg() {
        try {

            Connection connect = ConnectUtil.getConnect();
            //从连接中获取一个通道
            Channel channel = connect.createChannel();
            //String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
            channel.queueDeclare(QUEUE_NAME, false, false, true, null);
            //
            String msg = "消息1";
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());

            channel.close();
            connect.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void receiveMsg() {


        try {

            Connection connect = ConnectUtil.getConnect();
            Channel channel = connect.createChannel();

            //创建消费者
            DefaultConsumer consumer = new DefaultConsumer(channel) {

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println("接收到一个消息： " + message);
                }

            };
            channel.basicConsume(QUEUE_NAME, true, consumer);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
