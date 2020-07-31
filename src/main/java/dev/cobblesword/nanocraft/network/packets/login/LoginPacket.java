package dev.cobblesword.nanocraft.network.packets.login;

import dev.cobblesword.nanocraft.common.ProtocolUtils;
import dev.cobblesword.nanocraft.network.packets.Packet;
import dev.cobblesword.nanocraft.network.handler.PacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.ToString;

@ToString
public class LoginPacket extends Packet
{
    public String username;

    @Override
    public void decode(ByteBuf byteBuf)
    {
        this.username = ProtocolUtils.readString(byteBuf);
    }

    @Override
    public void encode(ByteBuf byteBuf)
    {

    }

    @Override
    public void handle(PacketHandler packetHandler)
    {
        packetHandler.handle(this);
    }
}
