package org.tian.nio.buffer;

import java.nio.IntBuffer;

/**
 * @author tianmh
 * @date create by 2021/10/26 13:33
 */
public class BasicBuffer {
    /**
     * position(int newPosition) 设置缓冲区位置
     * limit(int newLimit) 设置缓冲区限制
     * clear()  清除缓冲区，将各个标记恢复到初始状态
     * flip()   反转缓冲区
     * hasRemaining()   判断当前位置和限制位置之间是否有元素
     * isReadOnly()     判断缓冲区是否只读
     * hasArray()   判断缓冲区是否具有可访问的底层数组
     * array()  返回底层数组实现
     *
     */
    public static void main(String[] args) {
        //初始化一个容量为5的intBuffer
        IntBuffer intBuffer = IntBuffer.allocate(5);

        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i*2);
        }
        //从buffer读取数据
        intBuffer.flip();   //读写转换，写变为读
        intBuffer.position(1);  //重新设置position位置
        intBuffer.limit(3);     //重新设置limit限制

        while (intBuffer.hasRemaining()){
            //get一次，position会自动+1
            System.out.println(intBuffer.get());
        }
    }

}
