package cn.realtime.domain.impl.message.strategy;

import cn.realtime.domain.service.message.MessageHandleStrategy;
import cn.realtime.enums.message.MessageConvertEnum;
import cn.realtime.util.SpringUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import java.util.Map;

public abstract class AbstractMessageHandleStrategyFactory implements MessageHandleStrategy {

    public abstract void doMessageHandle(Map<String, Object> msgMap, ChannelHandlerContext ctx, TextWebSocketFrame msgFrame, ChannelGroup channelGroup);

    /**
     * 策略工厂:根据不同的msgCode执行不同的逻辑
     * @param msgMap
     * @param ctx
     * @param msgFrame
     * @param channelGroup
     */
    public static void doHandle(Map<String, Object> msgMap, ChannelHandlerContext ctx, TextWebSocketFrame msgFrame, ChannelGroup channelGroup) {
        Integer msgCode = (Integer) msgMap.get("msgCode");
        MessageConvertEnum convertEnum = MessageConvertEnum.findByMessageCode(msgCode);
        if (convertEnum != null && convertEnum.getHandleStrategy() != null) {
            AbstractMessageHandleStrategyFactory factory = SpringUtils.getBean(convertEnum.getHandleStrategy());
            factory.doMessageHandle(msgMap, ctx, msgFrame, channelGroup);
        } else {
            throw new RuntimeException("未注册");
        }
    }

}
