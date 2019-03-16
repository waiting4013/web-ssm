package tcp;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private static final int PORT = 8002;   //定义一个端口号
    public void listen() throws Exception{    //定义监听，抛出异常
        ServerSocket serversocket = new ServerSocket(PORT);
        Socket client = serversocket.accept();//调用方法接收数据
        OutputStream os = client.getOutputStream();
        System.out.println("开始与客户端交互数据");
        os.write(("hello world").getBytes());
        Thread.sleep(5000);    //模拟执行其他功能的占用时间
        System.out.println("结束交互");
        os.close();

    }


}
