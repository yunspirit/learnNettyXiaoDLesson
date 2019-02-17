import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


//多个线程共同使用这个handler
@ChannelHandler.Sharable
public class TcpCountHandler extends ChannelInboundHandlerAdapter {


    private AtomicInteger atomicInteger = new AtomicInteger();


    //定时任务去执行统计
    public TcpCountHandler(){

        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(()->{

            System.out.println("当前连接数为 = "+ atomicInteger.get());

        }, 0, 3, TimeUnit.SECONDS);

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        atomicInteger.incrementAndGet();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        atomicInteger.decrementAndGet();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("TcpCountHandler exceptionCaught");
        cause.printStackTrace();
    }
}
