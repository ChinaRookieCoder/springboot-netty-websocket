package cn.realtime.domain.impl.message.strategy;

import cn.realtime.base.MessageBody;
import cn.realtime.config.netty.UserChannelRel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 当websocket第一次打开时，初始化channel， 吧channel和userid关联起来
 */
@Service("socketConnectMsgHandleStrategy")
@Slf4j
public class SocketConnectMsgHandleStrategy extends AbstractMessageHandleStrategyFactory {
    public void doMessageHandle(Map<String, Object> msgMap, ChannelHandlerContext ctx, TextWebSocketFrame msgFrame, ChannelGroup channelGroup) {
        try {
            MessageBody messageBody = JSON.parseObject(JSON.toJSONString(msgMap.get("messageBody")), new TypeReference<MessageBody>() {});
            log.info("[UID:{}-请求连接]", messageBody.getSenderId());
            UserChannelRel.addChannel(messageBody.getSenderId(), ctx.channel()); // 添加用户
            log.info("[UID:{}-连接成功]", messageBody.getSenderId());
        } catch (Exception e) {
            log.error("处理连接逻辑异常", e);
        }
    }
}
