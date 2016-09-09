package com.tuolj.angel;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.DefaultEventExecutor;
import io.netty.util.concurrent.EventExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by zhongyangni on 2016/9/10.
 */
public class EchoClientHandler extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
        ctx.close();
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");

        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new Callable<Void>() {
            public Void call() throws Exception {
                Thread.sleep(2000);
                ctx.close();
                return  null;
            }
        });

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive");
    }
}
