package org.tian.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author tianmh
 * @date create by 2021/10/26 14:29
 */
public class NIOFileChannelBuffToBuff {
    private static final String ROOT_PATH = System.getProperty("user.dir");

    public static void main(String[] args) throws Exception{
        File file = new File(ROOT_PATH + "/output/file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        //通过fileInputStream获取对应的fileChannel
        FileChannel fileChannel = fileInputStream.getChannel();
        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        //将通道的数据读入到buffer
        fileChannel.read(byteBuffer);
        //将字节数据转换为string
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();

    }
}
