
package com.hzq.dubbo.netty.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

/**
 * socket服务端
 * FBI警告:
 * serversocket.accept()会生成新的客户端  原长连接丢失
 * 所以需要保存当前状态的socket链接  而且链接成功后客户端的socket也会变化
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/10/26 11:03
 */
public class BioServerInAndOut {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        AtomicReference<Socket> accept = new AtomicReference<>();
        Thread thread = new Thread(() -> {
            InputStream inputStream = null;
            try {
                System.out.println("服务器启动");
                while (true) {
                    if (accept.get() == null) {
                        accept.set(serverSocket.accept());
                    }

                    inputStream = accept.get().getInputStream();

                    BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                    byte[] bytes = new byte[1024];
                    bufferedInputStream.read(bytes);
                    String content = new String(bytes);
                    System.out.println("client：" + content);
                }
            } catch (IOException o) {
                System.out.println("io异常" + o);
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread thread1 = new Thread(() -> {
            OutputStream outputStream = null;
            try {
                while (true) {
                    if (accept.get() == null) {
                        continue;
                    }
                    outputStream = accept.get().getOutputStream();

                    Scanner scanner = new Scanner(System.in);
                    System.out.println("server:");
                    String s = scanner.nextLine();

                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

                    bufferedOutputStream.write(s.getBytes());
                    bufferedOutputStream.flush();
                    outputStream.flush();

                }
            } catch (IOException o) {
                System.out.println("io异常" + o);
            } finally {
                if (outputStream != null) {
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
