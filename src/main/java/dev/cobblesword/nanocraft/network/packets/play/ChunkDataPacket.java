package dev.cobblesword.nanocraft.network.packets.play;

import dev.cobblesword.nanocraft.common.ProtocolUtils;
import dev.cobblesword.nanocraft.network.packets.Packet;
import dev.cobblesword.nanocraft.network.handler.PacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ChunkDataPacket extends Packet
{
    public static final int WIDTH = 16, HEIGHT = 16, DEPTH = 256;
    private static final int SEC_DEPTH = 16;

    public int chunkX;
    public int chunkZ;
    public boolean continuous;
    public int bitmasked;
    public byte[] blockData;
    public int size;

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
        int byteSize = 0;
        final int numBlocks = WIDTH * HEIGHT * SEC_DEPTH;
        int sectionSize = numBlocks * 5 / 2;
        byteSize += SEC_DEPTH * sectionSize;
        byteSize += 256;  // + biomes

        byte[] tileData = new byte[byteSize];
        int pos = 0;

        // blocks
        int data = 0;
        int blockId = 3;
        int type = (blockId << 4) | data;
        int layers = 60;
        for (int i = 0; i < (16 * 16) * layers; i++)
        {
            tileData[pos++] = (byte) (type & 0xff);
            tileData[pos++] = (byte) (type >> 8);
        }
//
//        //X: 0, Y: 9, Z: 0
//        tileData[0 + ((16 * 16 * 17) * 2)] = (byte) (type & 0xff);
//        tileData[1 + ((16 * 16 * 17) * 2)] = (byte) (type >> 8);

//        //        //X: 0, Y: 9, Z: 0
//        tileData[0 + ((16 * 16 * 8) * 2)] = (byte) (type & 0xff);
//        tileData[1 + ((16 * 16 * 8) * 2)] = (byte) (type >> 8);

//        for (int i = 0; i < 16 * 16 * 30; i++)
//        {
//            tileData[pos++] = (byte) (type & 0xff);
//            tileData[pos++] = (byte) (type >> 8);
//        }


//        for (int i = 0; i < 16; i++)
//        {
//            tileData[pos++] = (byte) (type & 0xff);
//            tileData[pos++] = (byte) (type >> 8);
//        }


//        //X: 0, Y: 0, Z: 0
//        tileData[0] = (byte) (type & 0xff);
//        tileData[1] = (byte) (type >> 8);

//        //X: 1, Y: 0, Z: 0
//        tileData[2] = (byte) (type & 0xff);
//        tileData[3] = (byte) (type >> 8);

//        //X: 8, Y: 0, Z: 0
//        tileData[0 + 16] = (byte) (type & 0xff);
//        tileData[1 + 16] = (byte) (type >> 8);

//        //X: 0, Y: 0, Z: 1
//        tileData[0 + 16 * 2] = (byte) (type & 0xff);
//        tileData[1 + 16 * 2] = (byte) (type >> 8);

//        //X: 0, Y: 1, Z: 0
//        tileData[0 + ((16 * 16) * 2)] = (byte) (type & 0xff);
//        tileData[1 + ((16 * 16) * 2)] = (byte) (type >> 8);

//        //X: 0, Y: 6, Z: 0
//        tileData[0 + ((16 * 16 * 5) * 2)] = (byte) (type & 0xff);
//        tileData[1 + ((16 * 16 * 5) * 2)] = (byte) (type >> 8);

//        //X: 0, Y: 60, Z: 0
//        for (int i = 0; i < 60; i++) {
//            tileData[0 + ((16 * 16 * i) * 2)] = (byte) (type & 0xff);
//            tileData[1 + ((16 * 16 * i) * 2)] = (byte) (type >> 8);
//        }

//
//        // skylight
//        pos = 16 * 16 * 2;
//        for (int i = 0; i < 4096; i++) {
//            tileData[pos++] = (byte) (0);
//        }
//
//        //biome
//        for (int i = 0; i < 256; ++i) {
//            tileData[pos++] = 0;
//        }

        byteBuf.writeInt(this.chunkX);
        byteBuf.writeInt(this.chunkZ);
        byteBuf.writeBoolean(true);
        byteBuf.writeShort(0xFF);
        ProtocolUtils.writeVarInt(byteBuf, tileData.length);
        byteBuf.writeBytes(tileData);
    }

    @Override
    public void handle(PacketHandler packetHandler)
    {

    }
}
