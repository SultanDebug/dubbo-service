/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.netty.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.Set;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/11/2 16:05
 */
public class NioClient {
    private Selector selector;
    private int port = 8888;

    public NioClient() {
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress(InetAddress.getLocalHost(), port));

            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.forEach(selectionKey -> proccess(selectionKey));
                selectionKeys.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void proccess(SelectionKey selectionKey) {
        try {
            //
            if (selectionKey.isConnectable()) {
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                if (socketChannel.isConnectionPending()) {
                    socketChannel.finishConnect();
                    System.out.println("连接成功");

                    new Thread(() -> {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        while (true) {
                            try {
                                byteBuffer.clear();
                                Scanner scanner = new Scanner(System.in);
                                String str = scanner.nextLine();

                                byteBuffer.put(str.getBytes());

                                byteBuffer.flip();
                                socketChannel.write(byteBuffer);

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                socketChannel.register(selector, SelectionKey.OP_READ);
            } else if (selectionKey.isReadable()) {
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                byteBuffer.clear();
                int len;
                while ((len = socketChannel.read(byteBuffer)) > 0) {
                    String result = new String(byteBuffer.array());
                    System.out.println("收到服务器消息：" + result);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NioClient nioClient = new NioClient();
    }
}
