package dev.cobblesword.nanocraft.network.packets.play.player;

import dev.cobblesword.nanocraft.network.packets.Packet;
import dev.cobblesword.nanocraft.network.handler.PacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class KeepAlivePacket extends Packet
{
    public int token;

    @Override
    public int getProtocol() {
        return 0x00;
    }

    @Override
    public void decode(ByteBuf byteBuf)
    {
        this.token = byteBuf.readInt();
    }

    @Override
    public void encode(ByteBuf byteBuf)
    {
         byteBuf.writeInt(this.token);
    }

    @Override
    public void handle(PacketHandler packetHandler)
    {
        packetHandler.handle(this);
    }
}
