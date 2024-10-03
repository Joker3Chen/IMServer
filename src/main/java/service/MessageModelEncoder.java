package service;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;
import model.MessageModel;

/**
 * @author Joker3Chen
 * @date 2024/10/3 16:37
 */


public class MessageModelEncoder extends MessageToByteEncoder<MessageModel> {
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageModel msg, ByteBuf out) throws Exception {
        // 将 MessageModel 编码为 ByteBuf
        out.writeBytes(msg.getMessage().getBytes(CharsetUtil.UTF_8)); // 假设 MessageModel 有一个 String 类型的 content
        out.writeBytes(msg.getMessageType().getBytes(CharsetUtil.UTF_8)); // 假设 MessageModel 有一个 int 类型的 id
        out.writeBytes(msg.getMessageLength().getBytes(CharsetUtil.UTF_8)); // 假设 MessageModel 有一个 String 类型的 content
        out.writeBytes(msg.getSourceAddress().getBytes(CharsetUtil.UTF_8)); // 假设 MessageModel 有一个 String 类型的 content
        out.writeBytes(msg.getDestinationAddress().getBytes(CharsetUtil.UTF_8)); // 假设 MessageModel 有一个 String 类型的 content
        out.writeBytes(msg.getMiddleAddress().getBytes(CharsetUtil.UTF_8)); // 假设 MessageModel 有一个 String 类型的 content
        // ... 编码其他字段
    }
}

