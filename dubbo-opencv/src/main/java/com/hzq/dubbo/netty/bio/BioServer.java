
package com.hzq.dubbo.netty.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

/**
 * socket服务端
 * FBI警告:
 *      serversocket.accept()会生成新的客户端  原长连接丢失
 *      所以需要保存当前状态的socket链接  而且链接成功后客户端的socket也会变化
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/26 11:03
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
//        InputStream inputStream = null;
//        OutputStream outputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        ServerSocket serverSocket = new ServerSocket(8888);
        AtomicReference<Socket> accept = new AtomicReference<>();
        Thread thread = new Thread(()->{
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                System.out.println("服务器启动");
                while (true){
                    if(accept.get()==null){
                        accept.set(serverSocket.accept());
                    }

                    inputStream = accept.get().getInputStream();
                    outputStream = accept.get().getOutputStream();

                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

                /*byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                int size = 0;
                while ((size = bufferedInputStream.read(bytes)) > 0){
                    byteArrayOutputStream.write(bytes,0,size);
                }
                byte[] tmp = byteArrayOutputStream.toByteArray();
                String content = new String(tmp);*/
                    byte[] bytes = new byte[1024];
                    bufferedInputStream.read(bytes);
                    String content = new String(bytes);

                /*BufferedReader in=new BufferedReader(new InputStreamReader(accept.getInputStream()));
                String s1 = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((s1 = in.readLine())!=null){
                    stringBuilder.append(s1);
                }*/

                    System.out.println("服务端收到消息："+content);
                }
            }catch (IOException o){
                System.out.println("io异常"+o);
            }finally {
                if(inputStream != null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(outputStream != null){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                /*if(byteArrayOutputStream != null){
                    byteArrayOutputStream.close();
                }*/
            }
        });
        Thread thread1 = new Thread(()->{
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                while (true){
                    if(accept==null || accept.get()==null){
                        continue;
                    }
//                    Socket accept = serverSocket.accept();
                    inputStream = accept.get().getInputStream();
                    outputStream = accept.get().getOutputStream();

                    Scanner scanner = new Scanner(System.in);
                    String s = scanner.nextLine();

                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

                    bufferedOutputStream.write(s.getBytes());
                    bufferedOutputStream.flush();
                    outputStream.flush();

                }
            }catch (IOException o){
                System.out.println("io异常"+o);
            }finally {
                if(inputStream != null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(outputStream != null){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread.start();
        thread1.start();

    }
}
