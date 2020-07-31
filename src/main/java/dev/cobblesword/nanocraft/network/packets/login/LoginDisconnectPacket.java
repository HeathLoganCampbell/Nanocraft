package dev.cobblesword.nanocraft.network.packets.login;

import dev.cobblesword.nanocraft.common.ProtocolUtils;
import dev.cobblesword.nanocraft.network.packets.Packet;
import dev.cobblesword.nanocraft.network.handler.PacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class LoginDisconnectPacket extends Packet
{
    public String reason;

    @Override
    public int getProtocol() {
        return 0x0;
    }

    @Override
    public void decode(ByteBuf byteBuf)
    {

    }

    @Override
    public void encode(ByteBuf byteBuf)
    {
        ProtocolUtils.writeString(byteBuf, this.reason);
    }

    @Override
    public void handle(PacketHandler packetHandler)
    {

    }
}
