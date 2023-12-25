package cn.realtime.enums.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MessageStatusEnum {
    UNSENT(0,"未发送"),
    SENT(1,"已发送"),
    SENT_FAIL(4,"发送失败"),
    SENT_SUCCESS(6,"发送成功");
    private Integer code;
    private String status;
}
