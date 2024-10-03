package service;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
/**
 * @author Joker3Chen
 * @date 2024/10/3 16:44
 */


public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(
                new MessageModelEncoder() // 添加你的编码器
                // ... 其他 handler
        );
    }
}
