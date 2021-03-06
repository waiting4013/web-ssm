package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class selector {

    public void selector() throws IOException{

        ByteBuffer buffer=ByteBuffer.allocate(1024);
        Selector selector=Selector.open();
        ServerSocketChannel ssc=ServerSocketChannel.open();
        //设置为非阻塞方式
        ssc.configureBlocking(false);
        ssc.socket().bind(new InetSocketAddress(8080));
        //注册监听的事件
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            //获取所有key集合
            Set selectedKeys=selector.selectedKeys();
            Iterator it=selectedKeys.iterator();
            while (it.hasNext()){

                SelectionKey key=(SelectionKey)it.next();
                if((key.readyOps()&SelectionKey.OP_ACCEPT)==SelectionKey.OP_ACCEPT){
                    ServerSocketChannel ssChannel=(ServerSocketChannel)key.channel();
                    SocketChannel sc=ssChannel.accept();
                    sc.configureBlocking(false);
                    sc.register(selector,SelectionKey.OP_READ);
                    it.remove();
                }else if((key.readyOps()&SelectionKey.OP_READ)==SelectionKey.OP_READ){

                    SocketChannel sc=(SocketChannel)key.channel();
                    while (true){
                        buffer.clear();
                        int n=sc.read(buffer);
                        if(n<=0){
                            break;
                        }
                        buffer.flip();


                    }
                        it.remove();

                }

            }



        }


    }
}
