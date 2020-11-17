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
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/11/2 16:05
 */
public class NioServer {
    private Selector selector;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    private int port = 8888;

    public NioServer() {
        try {
            ServerSocketChannel channel = ServerSocketChannel.open();
            channel.bind(new InetSocketAddress(this.port));
            channel.configureBlocking(false);
            selector = Selector.open();
            channel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        Thread read = new Thread(() -> {
            try {
                while (true) {
                    int select = selector.select();
                    if (select == 0) {
                        continue;
                    }
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();

                    Iterator<SelectionKey> iterator = selectionKeys.iterator();

                    while (iterator.hasNext()) {
                        SelectionKey next = iterator.next();

                        read(next);

                        iterator.remove();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        Thread write = new Thread(() -> {
            try {
                while (true) {
                    int select = selector.select();
                    if (select == 0) {
                        continue;
                    }
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();

                    Iterator<SelectionKey> iterator = selectionKeys.iterator();

                    while (iterator.hasNext()) {
                        SelectionKey next = iterator.next();

                        write(next);

                        iterator.remove();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        read.start();
        write.start();

    }

    public void read(SelectionKey selectionKey) {
        try {
            if (selectionKey.isAcceptable()) {
                ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                SocketChannel accept = channel.accept();

                accept.configureBlocking(false);
                accept.register(selector, SelectionKey.OP_READ);
            } else if (selectionKey.isReadable()) {
                SocketChannel channel = (SocketChannel) selectionKey.channel();

                int len = channel.read(byteBuffer);
                if (len > 0) {
                    byteBuffer.flip();
                    String s = new String(byteBuffer.array(), 0, len);

                    //改成可写
                    selectionKey = channel.register(selector, SelectionKey.OP_WRITE);
                    selectionKey.attach(s);
                    System.out.println("读取消息：" + s);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void write(SelectionKey selectionKey) {
        try {
            if (selectionKey.isWritable()) {

                SocketChannel channel = (SocketChannel) selectionKey.channel();

                String s = (String) selectionKey.attachment();

                System.out.println("输出：" + s);

                Scanner scanner = new Scanner(System.in);

                String ret = scanner.nextLine();

                channel.write(ByteBuffer.wrap(ret.getBytes()));

                channel.register(selector, SelectionKey.OP_READ);

//            channel.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void proccess(SelectionKey selectionKey) {
        try {
            if (selectionKey.isAcceptable()) {
                ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                SocketChannel accept = channel.accept();

                accept.configureBlocking(false);
                accept.register(selector, SelectionKey.OP_READ);
            } else if (selectionKey.isReadable()) {
                SocketChannel channel = (SocketChannel) selectionKey.channel();

                int len = channel.read(byteBuffer);
                if (len > 0) {
                    byteBuffer.flip();
                    String s = new String(byteBuffer.array(), 0, len);

                    //改成可写
                    selectionKey = channel.register(selector, SelectionKey.OP_WRITE);
                    selectionKey.attach(s);
                    System.out.println("读取消息：" + s);

                }
            } else if (selectionKey.isWritable()) {

                SocketChannel channel = (SocketChannel) selectionKey.channel();

                String s = (String) selectionKey.attachment();

                System.out.println("输出：" + s);

                Scanner scanner = new Scanner(System.in);

                String ret = scanner.nextLine();

                channel.write(ByteBuffer.wrap(ret.getBytes()));

                channel.register(selector, SelectionKey.OP_READ);

//            channel.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NioServer().listen();
    }
}
