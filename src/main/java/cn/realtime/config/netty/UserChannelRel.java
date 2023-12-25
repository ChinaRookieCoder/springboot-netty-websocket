package cn.realtime.config.netty;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户id 与 channel关系处理
 */
@Slf4j
public class UserChannelRel {

    private static final ConcurrentHashMap<String, Channel> channelManager = new ConcurrentHashMap<String, Channel>();

    private static final String ATTR_KEY_USER_ID = "userId";

    /**
     * 判断Channel是否合法
     *
     * @return
     */
    public static boolean verifyChannel(Channel channel) {
        AttributeKey<String> key = AttributeKey.valueOf(ATTR_KEY_USER_ID);
        return (channel.hasAttr(key) || channel.attr(key).get() != null);
    }

    public static String getUserIdByChannel(Channel channel) {
        if (verifyChannel(channel)) {
            return channel.attr(AttributeKey.valueOf(ATTR_KEY_USER_ID)).get().toString();
        }
        return null;
    }

    public static void addChannel(String userId, Channel channel) {
        channelManager.put(userId, channel);
        // 将用户ID保存到Channel的Attribute中，用户和Channel绑定关系
        AttributeKey<String> key = AttributeKey.valueOf(ATTR_KEY_USER_ID);
        channel.attr(key).set(userId);
    }

    public static Channel getChannel(String userId) {
        return channelManager.get(userId);
    }

    public static void removeChannel(String userId) {
        channelManager.remove(userId);
        printChannelManager();
    }

    public static List<String> getOnLineUserIdList() {
        List<String> list = new ArrayList<String>();
        for (HashMap.Entry<String, Channel> entry : channelManager.entrySet()) {
            list.add(entry.getKey());
        }
        return list;
    }

    public static void printChannelManager() {
        for (HashMap.Entry<String, Channel> entry : channelManager.entrySet()) {
            log.info("[Channel管理器]userId: {}, channelId: {}\n", getUserIdByChannel(entry.getValue()), entry.getValue().id().asShortText());
        }
    }
}
