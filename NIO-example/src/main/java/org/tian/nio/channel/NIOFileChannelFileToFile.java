package org.tian.nio.channel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author tianmh
 * @date create by 2021/10/26 14:41
 */
public class NIOFileChannelFileToFile {
    private static final String ROOT_PATH = System.getProperty("user.dir");
//1.txt先自己写入内容并创建
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream(ROOT_PATH+"/output/1.txt");
        FileChannel inputChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream(ROOT_PATH+"/output/2.txt");
        FileChannel outputChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(521);

        while (true){
            //重置标志位
            byteBuffer.clear();
            int read = inputChannel.read(byteBuffer);
            if (read == -1) {
                break;
            }
            //反转buffer
            byteBuffer.flip();
            outputChannel.write(byteBuffer);
        }
        inputChannel.close();
        outputChannel.close();
        fileInputStream.close();
        fileOutputStream.close();
    }
}
