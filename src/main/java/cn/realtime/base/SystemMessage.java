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
public class SystemMessage extends MessageTemplate implements Serializable {
    private static final long serialVersionUID = 2345585565618351684L;
    /**
     * 业务来源
     */
    private Integer businessSource;
}
