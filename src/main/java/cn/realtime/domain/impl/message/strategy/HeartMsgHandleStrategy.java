package cn.realtime.domain.impl.message.strategy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("heartMsgHandleStrategy")
@Slf4j
public class HeartMsgHandleStrategy extends AbstractMessageHandleStrategyFactory {
    public void doMessageHandle(Map<String, Object> msgMap, ChannelHandlerContext ctx, TextWebSocketFrame msgFrame, ChannelGroup channelGroup) {
        //log.info("[UID:{}-心跳消息]", UserChannelRel.getUserIdByChannel(ctx.channel()));
    }
}
