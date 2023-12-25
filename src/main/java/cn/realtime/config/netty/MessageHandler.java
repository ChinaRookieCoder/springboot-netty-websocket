package cn.realtime.config.netty;

import cn.realtime.domain.impl.message.strategy.AbstractMessageHandleStrategyFactory;
import com.alibaba.fastjson.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

@Slf4j
public class MessageHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    // 用于记录和管理所有客户端的channel
    public static final ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) {
        String msgContent = textWebSocketFrame.text();
        // JSON转Map
        Map<String, Object> msgMap = JSON.parseObject(msgContent, Map.class);
        if (msgMap != null && msgMap.containsKey("msgCode")) {
            // 根据策略模式执行
            AbstractMessageHandleStrategyFactory.doHandle(msgMap, ctx, textWebSocketFrame, clients);
        }
    }

    //@Override
    //public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    //    clients.add(ctx.channel());
    //}

    //@Override
    //public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    //    // 当触发handlerRemoved，ChannelGroup会自动移除对应客户端的channel
    //    log.info("[{}-断开连接]客户端断开连接，channel对应的长id为:{}", ctx.channel().id().asShortText(), ctx.channel().id().asLongText());
    //    Attribute<Object> attr = ctx.channel().attr(AttributeKey.valueOf("userId"));
    //
    //    System.out.println("参数是"+attr.get().toString());
    //    log.info("[{}-断开连接]剩余数量:{}", ctx.channel().id().asShortText(), clients.size());
    //}

    /**
     * Channel处于活跃状态(已经连接到它的远程节点)。它现在可以接收和发送数据了
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    /**
     * Channel 处于非活跃状态，没有连接到远程节点
     *
     * @param ctx
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("[UID:{}-断开连接]客户端断开连接,剩余数量:{}", UserChannelRel.getUserIdByChannel(ctx.channel()), clients.size());
        String userId = UserChannelRel.getUserIdByChannel(ctx.channel());
        if (StringUtils.isNotBlank(userId)) {
            UserChannelRel.removeChannel(userId);
        }
    }

    /**
     * 异常处理
     *
     * @param ctx
     * @param cause
     * @throws Exception
     * @description: 发生异常后，关闭连接(关闭Channel),随后从channelGroup中移除
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
        clients.remove(ctx.channel());
    }
}
