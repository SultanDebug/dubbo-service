
package com.hzq.dubbo.netty.bio;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/26 11:03
 */
public class BioClient {
    public static void main(String[] args) throws IOException {


        Socket socket = new Socket("localhost", 8888);

        Thread thread = new Thread(() -> {
            OutputStream outputStream = null;
            try {
                Scanner scanner =new Scanner(System.in);
                while (true) {
                    System.out.println("输入消息:");
                    String s = scanner.nextLine();
                    outputStream = socket.getOutputStream();
                    BufferedOutputStream  write = new BufferedOutputStream (outputStream);

//                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

                    /*bufferedWriter.write(s);
                    bufferedWriter.flush();*/
                    write.write(s.getBytes());
                    write.flush();
                    outputStream.flush();
                }
            } catch (Exception o) {
                System.out.println(o);
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread1 = new Thread(() -> {
            InputStream inputStream = null;
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                while (true) {
                    inputStream = socket.getInputStream();
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                    /*byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bytes = new byte[1024];
                    int size = 0;
                    while ((size = bufferedInputStream.read(bytes)) > 0) {
                        byteArrayOutputStream.write(bytes, 0, size);
                    }
                    byte[] tmp = byteArrayOutputStream.toByteArray();
                    String content = new String(tmp);*/
                    byte[] bytes = new byte[1024];
                    bufferedInputStream.read(bytes);
                    String content = new String(bytes);

                    System.out.println("thread111客户端收到消息：" + content);
                }
            } catch (Exception o) {
                System.out.println(o);
            } finally {
                try {
                    assert inputStream != null;
                    inputStream.close();
                    assert byteArrayOutputStream != null;
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        thread1.start();


    }
}
