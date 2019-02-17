package net.xdclass.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

    private int port;

    public EchoServer(int port){
        this.port = port;
    }

    /**
     * 启动流程
     */
    public void run() throws InterruptedException {

        //配置服务端线程组   一个是前台接待，一个是服务员
        //此时尚未指定线程池中的线程数量，可以查看源码中
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap
                    //1、如果设置group父子关系，主从关系，则对应reactor模式的主从关系
                    //2、如果只有1个group，同时设置group的线程数为1，则为reactor模式的单线程模式
                    .group(bossGroup, workGroup)

                    //设置通道类型  比如阻塞、非阻塞
                    .channel(NioServerSocketChannel.class)
                    //比如对于每个连接，设置TCP参数
                    .option(ChannelOption.SO_BACKLOG,1024)
                    //初始化  比如刚进服务间会有一些人送水果饮料
                    //比如，bossGroup接收请求之后，会预先做一些channel(IO)操作
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //ch.pipeline().addLast(new EchoServerHandler());

                            ch.pipeline().addLast(new InboundHandler1());
                            ch.pipeline().addLast(new InboundHandler2());
                            ch.pipeline().addLast(new OutboundHandler1());
                            ch.pipeline().addLast(new OutboundHandler2());


                        }
                    });

            System.out.println("Echo 服务器启动ing");

            //绑定端口，同步等待成功
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();

            //等待服务端监听端口关闭
            channelFuture.channel().closeFuture().sync();
        }finally {

            //优雅退出，释放线程池
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }





    }


    public static void main(String [] args) throws InterruptedException {
        int port = 8080;
        if(args.length > 0){
            port = Integer.parseInt(args[0]);
        }

        new EchoServer(port).run();

    }

}
