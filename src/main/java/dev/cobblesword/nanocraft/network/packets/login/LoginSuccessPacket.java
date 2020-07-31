package dev.cobblesword.nanocraft.network.packets.login;

import dev.cobblesword.nanocraft.common.ProtocolUtils;
import dev.cobblesword.nanocraft.network.packets.Packet;
import dev.cobblesword.nanocraft.network.handler.PacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@ToString
@AllArgsConstructor
public class LoginSuccessPacket extends Packet
{
    public UUID uniqueId;
    public String username;

    @Override
    public int getProtocol() {
        return 2;
    }

    @Override
    public void decode(ByteBuf byteBuf)
    {
        // NOOP
    }

    @Override
    public void encode(ByteBuf byteBuf)
    {
        ProtocolUtils.writeString(byteBuf, this.uniqueId.toString());
        ProtocolUtils.writeString(byteBuf, this.username);
    }

    @Override
    public void handle(PacketHandler packetHandler)
    {

    }
}
