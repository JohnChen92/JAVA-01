package com.john.rpc.core.codec;

import com.alibaba.fastjson.JSON;
import com.john.rpc.core.meta.MsgBase;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

public class MessageDecoder extends ByteToMessageDecoder {

    private static final int MAGIC_NUMBER = 0x0CAFFEE0;
    private static final String CHAESET = "UTF-8";

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        if (byteBuf.readableBytes() < 14) {
            return;
        }
        // 标记开始读取位置
        byteBuf.markReaderIndex();
        int length = byteBuf.readInt();

        if (length < 0) {
            ctx.close();
            return;
        }

        if (byteBuf.readableBytes() < length) {
            // 重置到开始读取位置
            byteBuf.resetReaderIndex();
            return;
        }

        byte[] body = new byte[length];
        byteBuf.readBytes(body);
        String jsonStr = new String(body, CHAESET) ;
        out.add(JSON.parseObject(jsonStr, MsgBase.class));
    }
}
