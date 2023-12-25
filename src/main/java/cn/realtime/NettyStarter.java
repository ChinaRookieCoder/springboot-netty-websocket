package cn.realtime;

import cn.realtime.config.netty.WsServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 等spring加载完所有Bean之后，启动netty
 */
@Component
public class NettyStarter  implements ApplicationListener<ContextRefreshedEvent> {
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent() == null){
            WsServer.getInstance().start();
        }
    }
}
