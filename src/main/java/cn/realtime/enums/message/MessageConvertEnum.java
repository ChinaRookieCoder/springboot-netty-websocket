package cn.realtime.enums.message;

import cn.realtime.base.MessageTemplate;
import cn.realtime.base.OnlineUser;
import cn.realtime.base.SystemMessage;
import cn.realtime.base.UserMessage;
import cn.realtime.domain.impl.message.strategy.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageConvertEnum {

    SOCKET_CONNECT_MESSAGE(0, MessageTypeEnum.SOCKET_CONNECT, null, SocketConnectMsgHandleStrategy.class),
    HEART_BEAT_MESSAGE(1, MessageTypeEnum.HEART, null, HeartMsgHandleStrategy.class),
    ONLINE_MESSAGE(97,MessageTypeEnum.SYSTEM_NOTICE, OnlineUser.class,OnlineMsgHandleStrategy.class),
    USER_MESSAGE(98, MessageTypeEnum.CHAT, UserMessage.class, UserMsgHandleStrategy.class),
    SYSTEM_MESSAGE(99, MessageTypeEnum.SYSTEM_NOTICE, SystemMessage.class, SystemMsgHandleStrategy.class);

    private Integer messageCode;
    private MessageTypeEnum messageType;
    private Class<? extends MessageTemplate> clazz;
    private Class<? extends AbstractMessageHandleStrategyFactory> HandleStrategy;

    public static MessageConvertEnum findByMessageCode(Integer messageCode) {
        if (messageCode == null) {
            throw new IllegalArgumentException("messageCode不能为空");
        }
        for (MessageConvertEnum messageConvertEnum : MessageConvertEnum.values()) {
            if (messageConvertEnum.messageCode == messageCode) {
                return messageConvertEnum;
            }
        }
        throw new RuntimeException("messageCode未注册MessageConvertEnum");
    }
}
