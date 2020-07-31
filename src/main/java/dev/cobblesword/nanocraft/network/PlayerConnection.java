package dev.cobblesword.nanocraft.network;

import com.google.common.base.Preconditions;
import dev.cobblesword.nanocraft.network.handler.handlers.HandshakeHandler;
import dev.cobblesword.nanocraft.network.handler.PacketHandler;
import dev.cobblesword.nanocraft.network.packets.Packet;
import dev.cobblesword.nanocraft.network.packets.login.LoginDisconnectPacket;
import dev.cobblesword.nanocraft.network.pipeline.coders.MCDecoder;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import lombok.Getter;
import lombok.Setter;

import java.net.SocketAddress;

public class PlayerConnection extends SimpleChannelInboundHandler<Packet>
{
    @Setter
    private PacketHandler packetHandler;
    @Getter
    private SocketChannel channel;

    public PlayerConnection(SocketChannel channel)
    {
        this.packetHandler  = new HandshakeHandler(this);
        this.channel = channel;
    }

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception
    {
//        System.out.println("[Packet] " + packet.toString());
        packet.handle(packetHandler);
    }

    public void setState(NetworkState state)
    {
        channel.pipeline().get(MCDecoder.class).setState(state);
    }

    public void sendPacket(Packet packet)
    {
        if (channel.isActive())
        {
            channel.writeAndFlush(packet, channel.voidPromise());
        }
    }

    public void closeWith(Packet packet) {
        if (channel.isActive()) {
            channel.writeAndFlush(packet).addListener(ChannelFutureListener.CLOSE);
        }
    }

    private void ensureInEventLoop() {
        Preconditions.checkState(this.channel.eventLoop().inEventLoop(), "Not in event loop");
    }

    public EventLoop eventLoop() {
        return channel.eventLoop();
    }

    public void disconnect(String reason)
    {
        this.closeWith(new LoginDisconnectPacket(reason));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception
    {
        cause.printStackTrace();
        ctx.close();
    }

    public SocketAddress getAddress()
    {
        return this.channel.remoteAddress();
    }
}
