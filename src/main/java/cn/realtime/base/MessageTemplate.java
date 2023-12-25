package cn.realtime.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class MessageTemplate implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    private String msgId;

    /**
     * 消息Code
     * {@link cn.realtime.enums.message.MessageConvertEnum}
     */
    private Integer msgCode;

    /**
     * 消息状态
     * {@link cn.realtime.enums.message.MessageStatusEnum}
     */
    private Integer msgStatus;

    /**
     * 消息体
     */
    private MessageBody messageBody;
}
