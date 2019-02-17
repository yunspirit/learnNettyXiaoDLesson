package chapter7;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


public class ServerHandler extends ChannelInboundHandlerAdapter {


    //判断收到消息的次数
    private int counter;


    //此处是手动解析  使用decoder之后就不要用这个了
    //@Override
    //public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    //
    //    ByteBuf buf = (ByteBuf)msg;
    //    byte[] bytes = new byte[buf.readableBytes()];
    //    buf.readBytes(bytes);
    //
    //    String body = new String(bytes,"UTF-8")
    //            .substring(0,bytes.length - System.getProperty("line.separator").length());
    //
    //    System.out.println("服务端收到消息内容为：" + body + ", 收到消息次数：" + ++counter);
    //
    //}


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        String body = (String) msg;
        System.out.println("服务端收到消息内容为：" + body + ", 收到消息次数：" + ++counter);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
