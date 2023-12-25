package cn.realtime.domain.impl.message.strategy;

import cn.realtime.base.SystemMessage;
import cn.realtime.config.netty.UserChannelRel;
import cn.realtime.enums.message.MessageStatusEnum;
import cn.realtime.util.MessageUtil;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("systemMsgHandleStrategy")
@Slf4j
public class SystemMsgHandleStrategy extends AbstractMessageHandleStrategyFactory {

    public void doMessageHandle(Map<String, Object> msgMap, ChannelHandlerContext ctx, TextWebSocketFrame msgFrame, ChannelGroup channelGroup) {
        log.info("[UID:{}-系统]\n{}", UserChannelRel.getUserIdByChannel(ctx.channel()),msgFrame.text());
        SystemMessage systemMessageCommand = JSON.parseObject(msgFrame.text(), SystemMessage.class);
        systemMessageCommand.setMsgStatus(MessageStatusEnum.SENT_SUCCESS.getCode());
        MessageUtil.sendMessage(systemMessageCommand, channelGroup);
    }
}
