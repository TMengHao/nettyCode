package org.tian.nio.buffer;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author tianmh
 * @date create by 2021/10/26 16:03
 * Scattering：将数据写入到buffer，可以采用buffer数组，依次写入（分散）
 * Gathering：读取数据时，可以采用buffer数组，依次读
 */
public class ScatteringAndGathering {
    public static void main(String[] args) throws Exception{
        //使用serverSocketChannel 和 SocketChannel网络
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(66);
        //绑定端口到socket，并启动
        serverSocketChannel.socket().bind(inetSocketAddress);
        //创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);
        //等待客户端连接(telnet)
        SocketChannel socketChannel = serverSocketChannel.accept();

        int messageLength = 8;      //假定客户端接收8个字节
        while (true){
            int byteRead = 0;
            while (byteRead < messageLength){
                long length = socketChannel.read(byteBuffers);
                byteRead += 1;
                System.out.println("<--------------->");
                System.out.println("byteRead="+byteRead);
                Arrays.stream(byteBuffers).map(buffer -> "position="+buffer.position()+",limit="+buffer.limit()).forEach(System.out::println);
            }
            //将所有的buffer进行反转
            Arrays.asList(byteBuffers).forEach(Buffer::flip);
            //将数据读出显示到客户端
            long byteWrite = 0;
            while (byteWrite < messageLength){
                long length = socketChannel.write(byteBuffers);
                byteWrite += 1;
            }

            //将所有的buffer进行clear
            Arrays.asList(byteBuffers).forEach(Buffer::clear);

            System.out.println("byteRead="+byteRead+",byteWrite="+byteWrite+",messageLength="+messageLength);
        }
    }
}
