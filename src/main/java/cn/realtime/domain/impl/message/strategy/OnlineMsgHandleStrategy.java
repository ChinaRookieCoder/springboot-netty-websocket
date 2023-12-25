package cn.realtime.domain.impl.message.strategy;


import cn.realtime.base.MessageBody;
import cn.realtime.base.OnlineUser;
import cn.realtime.config.netty.UserChannelRel;
import cn.realtime.enums.message.MessageConvertEnum;
import cn.realtime.util.MessageUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("onlineMsgHandleStrategy")
public class OnlineMsgHandleStrategy extends AbstractMessageHandleStrategyFactory {
    public void doMessageHandle(Map<String, Object> msgMap, ChannelHandlerContext ctx, TextWebSocketFrame msgFrame, ChannelGroup channelGroup) {
        OnlineUser onlineUser=new OnlineUser();
        onlineUser.setMsgCode(MessageConvertEnum.ONLINE_MESSAGE.getMessageCode());
        onlineUser.setOnlineUsers(UserChannelRel.getOnLineUserIdList());
        onlineUser.setMessageBody(new MessageBody().setReceiverIdList(onlineUser.getOnlineUsers()));
        MessageUtil.sendMessage(onlineUser,channelGroup);
    }
}
