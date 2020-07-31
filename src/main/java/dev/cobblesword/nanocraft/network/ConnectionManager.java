package dev.cobblesword.nanocraft.network;

import dev.cobblesword.nanocraft.NanoServer;
import dev.cobblesword.nanocraft.network.pipeline.MCPipeline;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class ConnectionManager extends Thread
{
    private final Map<InetSocketAddress, Channel> endpoints = new HashMap<>();
    private EventLoopGroup bossGroup, workerGroup;

    @Override
    public void run()
    {
        this.bossGroup = new NioEventLoopGroup();
        this.workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MCPipeline())
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.IP_TOS, 0x18);

            bootstrap
                    .bind(NanoServer.PORT)
                    .addListener((ChannelFutureListener) future -> {
                        final Channel channel = future.channel();
                        if (future.isSuccess()) {
                            System.out.println("Listening on " + channel.localAddress());
                        }
                    })
                    .sync()
                    .channel()
                    .closeFuture()
                    .sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void shutdown()
    {
        stop();
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
