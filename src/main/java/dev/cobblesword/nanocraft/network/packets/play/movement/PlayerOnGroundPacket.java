package dev.cobblesword.nanocraft.network.packets.play.movement;

import dev.cobblesword.nanocraft.network.packets.Packet;
import dev.cobblesword.nanocraft.network.handler.PacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlayerOnGroundPacket extends Packet
{
    public boolean onGround;

    @Override
    public int getProtocol() {
        return 0x03;
    }

    @Override
    public void decode(ByteBuf byteBuf)
    {
        this.onGround = byteBuf.readBoolean();
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
