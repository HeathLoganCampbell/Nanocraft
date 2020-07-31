package dev.cobblesword.nanocraft.network.pipeline.coders;

import dev.cobblesword.nanocraft.common.ProtocolUtils;
import dev.cobblesword.nanocraft.network.NetworkState;
import dev.cobblesword.nanocraft.network.packets.handshake.HandshakePacket;
import dev.cobblesword.nanocraft.network.packets.login.LoginPacket;
import dev.cobblesword.nanocraft.network.packets.play.movement.PlayerOnGroundPacket;
import dev.cobblesword.nanocraft.network.packets.play.movement.PlayerPositionPacket;
import dev.cobblesword.nanocraft.network.packets.play.player.ChatMessagePacket;
import dev.cobblesword.nanocraft.network.packets.play.player.KeepAlivePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

public class MCDecoder extends MessageToMessageDecoder<ByteBuf>
{

    private NetworkState state = NetworkState.HANDSHAKE;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception
    {
        if (!in.isReadable()) return;

        int packetId = ProtocolUtils.readVarInt(in);
        if(this.state == NetworkState.HANDSHAKE)
        {
            if(packetId == 0)
            {
                HandshakePacket handshakePacket = new HandshakePacket();
                handshakePacket.decode(in);
                out.add(handshakePacket);
                return;
            }
        }
        else if(this.state == NetworkState.LOGIN)
        {
            if(packetId == 0)
            {
                LoginPacket loginPacket = new LoginPacket();
                loginPacket.decode(in);
                out.add(loginPacket);
                return;
            }
        }
        else if(this.state == NetworkState.PLAY)
        {
            if(packetId == 0x00)
            {
                KeepAlivePacket keepAlivePacket = new KeepAlivePacket();
                keepAlivePacket.decode(in);
                out.add(keepAlivePacket);
                return;
            }
            else if(packetId == 0x01)
            {
                ChatMessagePacket chatMessagePacket = new ChatMessagePacket();
                chatMessagePacket.decode(in);
                out.add(chatMessagePacket);
                return;
            }
            else if(packetId == 0x03)
            {
                PlayerOnGroundPacket playerOnGroundPacket = new PlayerOnGroundPacket();
                playerOnGroundPacket.decode(in);
                out.add(playerOnGroundPacket);
                return;
            }
            if(packetId == 0x04)
            {
                PlayerPositionPacket playerPositionPacket = new PlayerPositionPacket();
                playerPositionPacket.decode(in);
                out.add(playerPositionPacket);
                return;
            }
        }

        System.out.println("0x0" + Integer.toHexString(packetId) + " : " + packetId);
    }

    public void setState(NetworkState state)
    {
        this.state = state;
        System.out.println("State: " + state.name());
    }
}
