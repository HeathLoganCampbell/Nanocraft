package dev.cobblesword.nanocraft.network.packets.play.player;

import dev.cobblesword.nanocraft.common.ProtocolUtils;
import dev.cobblesword.nanocraft.network.packets.Packet;
import dev.cobblesword.nanocraft.network.handler.PacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessagePacket extends Packet
{
    public static final byte CHAT_TYPE = (byte) 0;

    public String message;
    private byte type;

    @Override
    public int getProtocol() {
        return 0x02;
    }

    @Override
    public void decode(ByteBuf byteBuf)
    {
        this.message = ProtocolUtils.readString(byteBuf);
//        this.type = byteBuf.readByte();
    }

    @Override
    public void encode(ByteBuf byteBuf)
    {
        ProtocolUtils.writeString(byteBuf, this.message);
        byteBuf.writeByte(this.type);
    }

    @Override
    public void handle(PacketHandler packetHandler)
    {
        packetHandler.handle(this);
    }
}
