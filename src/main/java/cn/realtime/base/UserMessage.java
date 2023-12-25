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
public class UserMessage extends MessageTemplate implements Serializable {

    private static final long serialVersionUID = 6509728790736789350L;

    /**
     * 用户名称
     */
    private String userName;
}
