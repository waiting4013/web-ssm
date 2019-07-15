package tcp;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    //定义一个端口号
    private static final int PORT = 8002;
    /**
     *
     *  定义监听，抛出异常
     */
    public void listen() throws Exception{
        ServerSocket serversocket = new ServerSocket(PORT);
        //调用方法接收数据
        Socket client = serversocket.accept();
        OutputStream os = client.getOutputStream();
        System.out.println("开始与客户端交互数据");
        os.write(("hello world").getBytes());
        //模拟执行其他功能的占用时间
        Thread.sleep(5000);
        System.out.println("结束交互");
        os.close();

    }


}
