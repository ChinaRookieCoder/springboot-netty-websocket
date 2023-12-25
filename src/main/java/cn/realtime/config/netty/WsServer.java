package cn.realtime.config.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * WebSocket服务
 */
@Component
@Slf4j
public class WsServer {
    /**静态内部类创建单例*/
    private static class SingletonWsServer{
        static final  WsServer INSTANCE = new WsServer();
    }
    public static WsServer getInstance(){
        return SingletonWsServer.INSTANCE;
    }

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap bootstrap;
    private ChannelFuture channelFuture;

    /**初始化配置*/
    public WsServer(){
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        bootstrap = new ServerBootstrap();
        bootstrap.group(mainGroup,subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new WsServerInitializer());
    }

    /**启动Websocket服务并绑定端口*/
    public void start(){
        this.channelFuture = bootstrap.bind(8888);
        log.info("################### WebSocket启动[端口8888] #######################");
        log.info("################### WebSocket启动[端口8888] #######################");
        log.info("################### WebSocket启动[端口8888] #######################");
    }
}
