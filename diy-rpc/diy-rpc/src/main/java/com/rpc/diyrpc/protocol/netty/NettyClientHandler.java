package com.rpc.diyrpc.protocol.netty;

import com.rpc.diyrpc.framework.Invocation;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@SuppressWarnings("deprecation")
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
	private Invocation invocation;

	public NettyClientHandler(Invocation invocation) {
		this.invocation = invocation;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		// 发送消息给服务端
		ctx.writeAndFlush(invocation);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println(msg);
		ctx.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("【client exception is general】");
	}
}
