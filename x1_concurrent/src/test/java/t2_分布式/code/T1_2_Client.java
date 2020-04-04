package t2_分布式.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class T1_2_Client {

    public static void main(String[] args) {
        try {
            //1 连接服务
            Socket socket = new Socket("localhost", 9000);
            //2 在当前连接上写入
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //outToServer.println("hello server");
            //读取控制台内容
            BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));
            String consoleLine = consoleIn.readLine();
            while (!consoleLine.equals("bye")) {
                PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);

                outToServer.println(consoleLine);//往服务端写
                //outToServer.flush();

                String fromServer = inFromServer.readLine();
                System.out.println("服务端发过来的内容:" + fromServer);
                consoleLine = consoleIn.readLine();


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
