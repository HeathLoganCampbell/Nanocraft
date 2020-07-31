package dev.cobblesword.nanocraft.network.packets.play.movement;

import dev.cobblesword.nanocraft.network.handler.PacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlayerPositionPacket extends PlayerOnGroundPacket
{
    public double x, y, z;

    @Override
    public int getProtocol() {
        return 4;
    }

    @Override
    public void decode(ByteBuf byteBuf)
    {
        this.x = byteBuf.readDouble();
        this.y = byteBuf.readDouble();
        this.z = byteBuf.readDouble();
        super.decode(byteBuf);
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
