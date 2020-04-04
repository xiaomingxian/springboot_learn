package t2_分布式.code;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class T1_1_Server {

    public static void main(String[] args) {
        try {
            //1 ip port
            ServerSocket serverSocket = new ServerSocket(9000);
            //2 接受客户端连接(阻塞:当前没有连接建立的时候这里处于阻塞状态)
            Socket socket = serverSocket.accept();

            //3 拿到输入流
            BufferedReader clientIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //4-1 获得客户端的输入信息
            //System.out.println(clientIn.readLine());

            //4-2 进行输出
            //通过控制台拿数据
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
            String consoleLine = consoleIn.readLine();
            while (!consoleLine.equals("bye")) {
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());

                printWriter.write(consoleLine);//往客户端写
                printWriter.flush();

                System.out.println("从客户端读取的内容:" + clientIn.readLine());
                //重新获取控制台内容
                consoleLine = consoleIn.readLine();
                System.out.println("------->>>>" + consoleLine);
            }
            //5 关闭...
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
