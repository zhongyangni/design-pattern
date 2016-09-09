package com.tuolj.angel;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by zhongyangni on 2016/9/10.
 */
public class EchoClient {
    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        try {
            b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new EchoClientHandler());
                    }
                });

            ChannelFuture f = b.connect("127.0.0.1", 8080).sync();

            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            group.shutdownGracefully();
        }
    }
}
