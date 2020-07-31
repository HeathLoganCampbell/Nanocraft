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
public class PlayerPositionAndLookPacket extends Packet
{
    public double x, y, z;
    public float yaw, pitch;
    public boolean relative;

    @Override
    public int getProtocol() {
        return 0x08;
    }

    @Override
    public void decode(ByteBuf byteBuf)
    {

    }

    @Override
    public void encode(ByteBuf byteBuf)
    {
        byteBuf.writeDouble(this.x);
        byteBuf.writeDouble(this.y);
        byteBuf.writeDouble(this.z);
        byteBuf.writeFloat(this.yaw);
        byteBuf.writeFloat(this.pitch);
        byteBuf.writeBoolean(this.relative);
    }

    @Override
    public void handle(PacketHandler packetHandler)
    {

    }
}
