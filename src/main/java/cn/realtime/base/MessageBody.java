package cn.realtime.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class MessageBody implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 发送者Id
     */
    private String senderId;

    /**
     * 接受者Id
     */
    private List<String> receiverIdList;

    /**
     * 排除的接收者Id(不给谁发)
     */
    private List<String> ignoreReceiverIdList;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String msgContent;
}
