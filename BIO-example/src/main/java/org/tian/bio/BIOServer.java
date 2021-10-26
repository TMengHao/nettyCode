package org.tian.bio;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tianmh
 * @date create by 2021/10/26 12:23
 */
public class BIOServer {
//使用telnet进行连接
    public static void main(String[] args) throws Exception{
        ExecutorService executorService = Executors.newCachedThreadPool();
        //创建socket
        ServerSocket serverSocket = new ServerSocket(666);

        System.out.println("服务器启动了"+Thread.currentThread().getName());

        while (true){
            //监听客户端连接
            final Socket socket = serverSocket.accept();
            System.out.println("连接到客户端");
            //创建一个线程，与之通讯
            executorService.execute(() -> {
                handler(socket);    //与客户端通讯的方法
            });
        }
    }

    private static void handler(Socket socket) {
        try {
            System.out.println("线程id="+Thread.currentThread().getId()+
                    ",名字="+Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            //通过socket获取输入流
            InputStream inputStream = socket.getInputStream();
            int read;
            //循环读取客户端发送的数据
            while ((read = inputStream.read(bytes)) != -1){
                System.out.println("线程id="+Thread.currentThread().getId()+
                        ",名字="+Thread.currentThread().getName());
                //输出客户端发送的数据
                System.out.println(new String(bytes,0,read));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
