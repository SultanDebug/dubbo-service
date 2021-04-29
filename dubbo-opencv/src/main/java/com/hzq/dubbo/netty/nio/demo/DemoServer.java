
package com.hzq.dubbo.netty.nio.demo;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/11/25 11:18
 */
public class DemoServer {
    static Selector selector;

    static {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8899));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            int select = selector.select();
            if(select == 0){
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel accept = channel.accept();
                    accept.configureBlocking(false);
                    accept.register(selector,SelectionKey.OP_READ);
                    System.out.println("服务端可读");
                }else if(selectionKey.isReadable()){
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    //读取数据
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int size = channel.read(byteBuffer);
                    if(size>0){
                        String s = new String(byteBuffer.array());
                        System.out.println("服务端收到消息："+s);
                    }

                    //注册写事件
                    channel.register(selector,SelectionKey.OP_WRITE);
                    System.out.println("服务端可写");
                }else if(selectionKey.isWritable()){
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    System.out.println("输入：");
                    Scanner scanner = new Scanner(System.in);
                    String ret = scanner.nextLine();
                    channel.write(ByteBuffer.wrap(ret.getBytes()));
                    channel.register(selector,SelectionKey.OP_READ);
                    System.out.println("服务端写完可读");
                }
                iterator.remove();
            }
        }

        /*ExecutorService executor =
                new ThreadPoolExecutor(2,
                        2,
                        0L,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingDeque<>());*/

        /*Thread t1 = new Thread (() -> {
            while (true) {
                try {
                    int select = selector.select();
                    if (select == 0) {
                        continue;
                    }
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                            SocketChannel accept = channel.accept();
                            accept.configureBlocking(false);
                            accept.register(selector, SelectionKey.OP_READ);
                            //accept.register(selector, SelectionKey.OP_WRITE);
                            System.out.println("服务端可读");
                            iterator.remove();
                        } else if (selectionKey.isReadable()) {
                            SocketChannel channel = (SocketChannel) selectionKey.channel();
                            //读取数据
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            int size = channel.read(byteBuffer);
                            if (size > 0) {
                                String s = new String(byteBuffer.array());
                                System.out.println("服务端收到消息：" + s);
                            }

                            //注册写事件
                            channel.register(selector, SelectionKey.OP_READ);
                            System.out.println("服务端可写");
                            iterator.remove();
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    int select = selector.select();
                    if (select == 0) {
                        continue;
                    }
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isWritable()) {
                            SocketChannel channel = (SocketChannel) selectionKey.channel();
                            System.out.println("输入：");
                            Scanner scanner = new Scanner(System.in);
                            String ret = scanner.nextLine();
                            channel.write(ByteBuffer.wrap(ret.getBytes()));
                            channel.register(selector, SelectionKey.OP_WRITE);
                            System.out.println("服务端写完可读");
                            iterator.remove();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        t1.start();
        t2.start();*/




    }
}
