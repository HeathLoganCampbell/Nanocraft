package dev.cobblesword.nanocraft.network.packets.play;

import dev.cobblesword.nanocraft.network.packets.Packet;
import dev.cobblesword.nanocraft.network.handler.PacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class ChunkBulkPacket extends Packet
{
    public int chunkX;
    public int chunkZ;
    public boolean groundUpContinuous;
    public int primaryBitMap;
    public int size;
    public byte[] chunkData;

    @Override
    public int getProtocol() {
        return 0x21;
    }

    @Override
    public void decode(ByteBuf byteBuf)
    {
        // NOOP
    }

    @Override
    public void encode(ByteBuf byteBuf)
    {
        byteBuf.writeInt(chunkX);
        byteBuf.writeInt(chunkZ);
        byteBuf.writeBoolean(groundUpContinuous);
        byteBuf.writeShort(primaryBitMap);
        byteBuf.writeShort(size);
        byteBuf.writeInt(chunkData.length);
        byteBuf.writeBytes(chunkData);
    }

    @Override
    public void handle(PacketHandler packetHandler)
    {

    }
}
