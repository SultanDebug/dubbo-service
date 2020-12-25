/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.dubbo.netty.nio.demo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/11/25 11:18
 */
public class DemoClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();

        socketChannel.register(selector,SelectionKey.OP_CONNECT);

        socketChannel.connect(new InetSocketAddress(InetAddress.getLocalHost(), 8899));

        while (true) {

            int select = selector.select();
            if(select == 0){
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if(selectionKey.isConnectable()){
                    socketChannel.finishConnect();
                    /*SocketChannel channel = (SocketChannel) selectionKey.channel();
                    channel.configureBlocking(false);*/
                    socketChannel.register(selector,SelectionKey.OP_WRITE);
                    System.out.println("客户端可写");
                } else if(selectionKey.isWritable()){
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    System.out.println("输入：");
                    Scanner scanner = new Scanner(System.in);
                    String ret = scanner.nextLine();
                    channel.write(ByteBuffer.wrap(ret.getBytes()));
                    socketChannel.register(selector,SelectionKey.OP_READ);
                    System.out.println("客户端可读");
                }else if(selectionKey.isReadable()){
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    //读取数据
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int size = channel.read(byteBuffer);
                    if(size>0){
                        String s = new String(byteBuffer.array(),0, size);
                        System.out.println("客户端收到消息："+s);
                    }
                    socketChannel.register(selector,SelectionKey.OP_WRITE);
                    System.out.println("客户端读完可写");
                }
            }
//            selectionKeys.clear();
        }
    }
}
