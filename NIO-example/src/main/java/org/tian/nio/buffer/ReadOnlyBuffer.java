package org.tian.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author tianmh
 * @date create by 2021/10/26 15:12
 */
public class ReadOnlyBuffer {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        for (int i = 0; i < 64; i++) {
            buffer.put((byte) i);
        }
        //反转buffer进行读取
        buffer.flip();
        //得到一个只读的buffer
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());
        //读取数据
        while (readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }
        //只读buffer不能写入数据，会报ReadOnlyBufferException
        readOnlyBuffer.put((byte) 100);
    }

}
