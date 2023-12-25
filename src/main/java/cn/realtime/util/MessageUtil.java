package cn.realtime.util;

import cn.realtime.base.MessageTemplate;
import cn.realtime.config.netty.UserChannelRel;
import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Iterator;
import java.util.List;

public class MessageUtil {

    /**
     * 发送消息
     * @param message
     * @param channelGroup
     * @param <T>
     */
    public static <T extends MessageTemplate> void sendMessage(T message, ChannelGroup channelGroup) {
        if (message != null && message.getMessageBody() != null) {
            List<String> receiverIdList = message.getMessageBody().getReceiverIdList();
            Iterator<String> iterator = receiverIdList.iterator();
            while (iterator.hasNext()) {
                Channel receiverChannel = UserChannelRel.getChannel(iterator.next());
                // TODO:用户在线
                if (receiverChannel != null && channelGroup.find(receiverChannel.id()) != null) {
                    receiverChannel.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
                }else{
                    // TODO:用户离线
                }
            }
        }
    }
}
