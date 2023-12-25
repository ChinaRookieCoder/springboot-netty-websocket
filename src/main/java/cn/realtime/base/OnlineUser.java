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
public class OnlineUser extends MessageTemplate implements Serializable {
    private static final long serialVersionUID = 2880625166485502924L;

    /**
     * 在线用户ID
     */
    private List<String> onlineUsers;
}
