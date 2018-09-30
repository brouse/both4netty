package org.both.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * server 启动类
 * Created by zhangheng Created on 2018/9/27
 */
public class NettyServer {

    private final static int port = 8000;

    public static void start() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        //创建连接的线程一个就够了
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        //读取连接中内容的线程稍微大点
        NioEventLoopGroup worker = new NioEventLoopGroup();

        //开始注册并初始化服务
        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new StringDecoder());
                        nioSocketChannel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
                                System.out.println(s);
                            }
                        });
                    }
                })
                .bind(port);

        System.out.println("server start port:" + port);
    }

    public static void main(String[] args) {
        start();
    }

}
