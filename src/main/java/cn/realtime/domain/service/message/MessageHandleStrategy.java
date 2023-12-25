package cn.realtime.domain.service.message;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Map;

/**
 * 消息处理策略接口
 */
public interface MessageHandleStrategy {
    /**
     * 执行消息处理接口
     *
     * @param msgMap
     * @param msgFrame
     * @param channelGroup
     */
    void doMessageHandle(Map<String, Object> msgMap, ChannelHandlerContext ctx, TextWebSocketFrame msgFrame, ChannelGroup channelGroup);
}
