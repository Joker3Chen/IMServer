package service;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import com.alibaba.fastjson.JSON;
import model.MessageModel;

import java.nio.charset.StandardCharsets;

/**
 * @author Joker3Chen
 * @date 2024/10/2 2:25
 */
public class Decoder extends SimpleChannelInboundHandler<ByteBuf> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) {

        // 进入到这个channelRead0方法就表明之前的LengthFieldBasedFrameDecoder解码器已经把有效body数据解码出来了, 这个channelRead0方法得到的就是body有效数据.
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);

        String json = new String(bytes, StandardCharsets.UTF_8);
        MessageModel v = JSON.parseObject(json, MessageModel.class);
        ctx.fireChannelRead(v);
    }

}
