package cn.realtime.config.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * 用于检测channel的心跳handler  继承ChannelInboundHandlerAdapter，从而不需要实现channelRead0方法
 */
@Slf4j
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 判断evt是否是IdleStateEvent（用于触发用户事件，包含 读空闲/写空闲/读写空闲 ）
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt; // 强制类型转换
            switch (event.state()){
                case READER_IDLE:
                    log.info("[UID:{}-心跳消息]进入读空闲...",UserChannelRel.getUserIdByChannel(ctx.channel()));
                    break;
                case WRITER_IDLE:
                    log.info("[UID:{}-心跳消息]进入写空闲...",UserChannelRel.getUserIdByChannel(ctx.channel()));
                    break;
                case ALL_IDLE:
                    log.info("[UID:{}-心跳消息]超时60主动关闭资源...",UserChannelRel.getUserIdByChannel(ctx.channel()));
                    // todo:读写空闲，服务端主动关闭资源，防止资源浪费
                    ctx.channel().close();
                    break;
            }
        }
        super.userEventTriggered(ctx, evt);
    }
}
