package service;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import model.MessageModel;
/**
 * @author Joker3Chen
 * @date 2024/10/2 2:25
 */
public class ServerHandler extends SimpleChannelInboundHandler<MessageModel> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageModel msg) throws Exception {
        System.out.println(msg);
        ChannelFuture future = ctx.writeAndFlush(msg);
        future.addListener((ChannelFutureListener) future1 -> {
            if (future1.isSuccess()) {
                System.out.println("Write successful");
            } else {
                System.err.println("Write failed");
                future1.cause().printStackTrace();
            }
        });
    }
}