package tcp;

import java.net.InetAddress;
import java.net.Socket;

public class TCPClient {


    private static final int PORT=8002;//服务器端口号
    public void connect() throws Exception{
        //创建一个socket并连接到给出地址和端口号的计算机
        Socket client = new Socket(InetAddress.getLocalHost(),PORT);
        //得到接受数据的流
        java.io.InputStream is = client.getInputStream();
        byte[] buf = new byte[1024];//定义缓冲区
        int len = is.read(buf);  //读入缓冲区
        System.out.println(new String(buf,0,len));//将缓冲区中的数据输出
        client.close();   //关闭释放资源
    }


}
