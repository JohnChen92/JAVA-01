package com.john.rpc.core.handler;

import com.john.rpc.core.meta.CallMsg;
import com.john.rpc.core.meta.MsgBase;
import com.john.rpc.core.meta.ResultMsg;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import java.lang.reflect.Method;

public class RpcServerHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("123123:" + msg);
    Object[] objects = ((MsgBase) msg).objMsg();
    String[] params = (String[]) objects[3];
    Class[] clazzs = (Class[]) objects[4];
    //进行方法的调用处理
    Class clazz = Class.forName(String.valueOf(objects[1]));
    Method meth = clazz.getMethod(String.valueOf(objects[2]), clazzs);
    Object invoke = meth.invoke(clazz.newInstance(), params);
    send(ctx, invoke, "1");
  }

  /**
   * 异常处理
   * @param ctx
   * @param cause
   */
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    send(ctx, cause.getCause(), "0");
    cause.printStackTrace();
    ctx.close();
  }


  /**
   * 发送的返回值
   * @param ctx     返回
   * @param context 消息
   * @param status 状态
   */
  private void send(ChannelHandlerContext ctx, Object context, String status) {
    ResultMsg msg = new ResultMsg();
    msg.setO(context);
    msg.setResultType(status);
    ctx.writeAndFlush(msg).addListener(ChannelFutureListener.CLOSE);
  }

}
