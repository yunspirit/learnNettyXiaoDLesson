package chapter7;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {


    //测试带上换行符号的数据
    //@Override
    //public void channelActive(ChannelHandlerContext ctx) throws Exception {
    //
    //    ByteBuf mes = null;
    //    //加上换行符号
    //    byte [] req = ("xdclass.net"+System.getProperty("line.separator")).getBytes();
    //    //连续发送
    //    for(int i=0; i< 10; i++){
    //        mes = Unpooled.buffer(req.length);
    //        mes.writeBytes(req);
    //        ctx.writeAndFlush(mes);
    //    }
    //
    //
    //}



    //自定义分隔符
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        String message = "Netty is a NIO client server framework which enables quick&_" +
                "and easy development of network applications&_ " +
                "such as protocol servers and clients.&_" +
                " It greatly simplifies and streamlines&_" +
                "network programming such as TCP and UDP socket server.&_";
        //最后一行要添加&_,不然会报错

        ByteBuf mes = null;
        mes = Unpooled.buffer(message.getBytes().length);
        mes.writeBytes(message.getBytes());
        ctx.writeAndFlush(mes);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }
}
