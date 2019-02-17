package net.xdclass.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

//当服务端有数据进来的时候
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf data = (ByteBuf) msg;

        System.out.println("服务端收到数据: "+ data.toString(CharsetUtil.UTF_8));

        ctx.writeAndFlush(data);

        //测试  数据写出去
        //方式1
        Channel channel = ctx.channel();
        channel.writeAndFlush(Unpooled.copiedBuffer("小滴课堂 xdclass.net",CharsetUtil.UTF_8) );


        //方式2
        ChannelPipeline pipeline = ctx.pipeline();
        pipeline.writeAndFlush(Unpooled.copiedBuffer("小滴课堂 xdclass.net",CharsetUtil.UTF_8) );

        //方式3
        ctx.writeAndFlush(Unpooled.copiedBuffer("小滴课堂 xdclass.net",CharsetUtil.UTF_8) );


        //ctx.fireChannelRead(data);   //调用下个handler



    }



    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        System.out.println("EchoServerHandle channelReadComplete");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
