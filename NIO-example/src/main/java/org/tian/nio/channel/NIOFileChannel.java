package org.tian.nio.channel;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author tianmh
 * @date create by 2021/10/26 14:07
 */
public class NIOFileChannel {

    private static final String ROOT_PATH = System.getProperty("user.dir");

    public static void main(String[] args) throws Exception{
        String str = "Hello,世界!";
        //创建输出流->channel
        FileOutputStream fileOutputStream = new FileOutputStream(ROOT_PATH+"/output/file01.txt");
        //通过fileOutputStream 获取对应的抽象fileChannel
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建一个缓冲区 byteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //将str放入byteBuffer
        byteBuffer.put(str.getBytes());

        //对byteBuffer进行反转
        byteBuffer.flip();

        //将byteBuffer数据写入到fileChannel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }

}
