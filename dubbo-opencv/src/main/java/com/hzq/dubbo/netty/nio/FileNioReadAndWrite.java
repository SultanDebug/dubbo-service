
package com.hzq.dubbo.netty.nio;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/11/2 11:24
 */
public class FileNioReadAndWrite {
    public static void main(String[] args) throws FileNotFoundException {
        URL fileNio = FileNioReadAndWrite.class.getClassLoader().getResource("FileNio");
        FileInputStream inputStream = new FileInputStream(fileNio.getPath());
        FileChannel channel = inputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byte[] bytes = new byte[8];

        ByteOutputStream byteOutputStream = new ByteOutputStream();

        Thread t1 = new Thread(()->{
            try {
                int read = channel.read(byteBuffer);
                while(true){
                    System.out.println("read : "+read);
                    byteBuffer.flip();

                    if(byteBuffer.hasRemaining()){
                        byteBuffer.get(bytes,0,read);
                        byteOutputStream.write(bytes,0,read);
                    }

                    Thread.sleep(1000);
                    byteBuffer.clear();
                    read = channel.read(byteBuffer);

                    if(read == -1){
                        String s = new String(byteOutputStream.getBytes());
                        System.out.println("final data : "+s);
                        break;
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()->{
            try {
                System.out.println("开始写入");
                String s = "hello world";
                byteBuffer.put(s.getBytes());
                channel.write(byteBuffer);
                byteBuffer.flip();
                System.out.println("写入结束");
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        t1.start();
//        t2.start();
    }
}
