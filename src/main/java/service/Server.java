package service;

/**
 * @author Joker3Chen
 * @date 2024/10/2 2:24
 */
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class Server {


    public static void main(String[] args) throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {

            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) {
                            ChannelPipeline channelPipeline = ch.pipeline();
                            channelPipeline.addLast(new MessageModelEncoder());
                            // 按照我们自定义的协议,将有效数据解码出来,解码成有效的字节数据
                            channelPipeline.addLast(new LengthFieldBasedFrameDecoder(1024, 0, 4, 0, 4));
                            // 将解码出来的字节数据转换成实体对象
                            channelPipeline.addLast(new Decoder());
                            // 处理实体对象
                            channelPipeline.addLast(new ServerHandler());

                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind("127.0.0.1", 8082).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

