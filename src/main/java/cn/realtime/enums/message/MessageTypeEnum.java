package cn.realtime.enums.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageTypeEnum {
    /** 系统通知 */
    SYSTEM_NOTICE(100,"系统通知"),
    /** 聊天消息 */
    CHAT(102,"聊天消息"),
    /** 心跳消息 */
    HEART(103,"心跳消息"),
    /** SOCKET连接消息 */
    SOCKET_CONNECT(104,"连接消息");
    private Integer code;
    private String type;
}
