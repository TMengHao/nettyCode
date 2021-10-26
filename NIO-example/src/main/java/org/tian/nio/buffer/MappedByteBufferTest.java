package org.tian.nio.buffer;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author tianmh
 * @date create by 2021/10/26 15:20
 * 直接在内存中修改文件，操作系统不需要拷贝一次
 */
public class MappedByteBufferTest {
    private static final String ROOT_PATH = System.getProperty("user.dir");

    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile(ROOT_PATH+"/output/1.txt","rw");

        FileChannel channel = randomAccessFile.getChannel();
        /**
         * @param1 FileChannel.MapMode.READ_WRITE使用读写模式
         * @param2 可以直接修改的起始位置
         * @param3 映射到内存的大小(不是索引),即将文件的多少个字节映射到内存
         * 实际类型为DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE,0,5);

        mappedByteBuffer.put(0,(byte) 'L');
        mappedByteBuffer.put(3,(byte) '4');
        //mappedByteBuffer.put(5,(byte) '4');   //修改5号位置会异常,按照下标索引
        randomAccessFile.close();
    }
}
