package dev.cobblesword.nanocraft.network.packets;

import dev.cobblesword.nanocraft.network.handler.PacketHandler;
import io.netty.buffer.ByteBuf;

public class Packet
{
    public int getProtocol()
    {
        return 0;
    }

    public void decode(ByteBuf byteBuf)
    {

    }

    public void encode(ByteBuf byteBuf)
    {

    }

    public void handle(PacketHandler packetHandler)
    {

    }
}
