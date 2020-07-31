package dev.cobblesword.nanocraft.network.packets.handshake;

import dev.cobblesword.nanocraft.common.ProtocolUtils;
import dev.cobblesword.nanocraft.network.packets.Packet;
import dev.cobblesword.nanocraft.network.handler.PacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.ToString;

@ToString
public class HandshakePacket extends Packet
{
    public int protocolVersion;
    public String address;
    public int port;
    public int status;

    @Override
    public void decode(ByteBuf byteBuf)
    {
        this.protocolVersion = ProtocolUtils.readVarInt(byteBuf);
        this.address = ProtocolUtils.readString(byteBuf);
        this.port = byteBuf.readUnsignedShort();
        this.status = ProtocolUtils.readVarInt(byteBuf);
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
