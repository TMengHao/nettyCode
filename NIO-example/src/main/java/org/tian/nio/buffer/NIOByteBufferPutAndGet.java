package org.tian.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @author tianmh
 * @date create by 2021/10/26 15:08
 */
public class NIOByteBufferPutAndGet {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);
        //放入类型化数据
        buffer.putInt(100);
        buffer.putLong(9L);
        buffer.putChar('田');
        buffer.putShort((short) 18);
        //反转buffer
        buffer.flip();
        //输出buffer内容，必须和上面放入顺序和类型一致
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
    }
}
