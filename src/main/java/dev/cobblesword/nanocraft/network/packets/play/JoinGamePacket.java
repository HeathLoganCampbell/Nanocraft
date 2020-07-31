package dev.cobblesword.nanocraft.network.packets.play;

import dev.cobblesword.nanocraft.common.ProtocolUtils;
import dev.cobblesword.nanocraft.network.packets.Packet;
import dev.cobblesword.nanocraft.network.handler.PacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class JoinGamePacket extends Packet
{
    public int entityId;
    public int gamemode;
    public int dimension = 0;
    public int difficulty = 0;
    public int maxPlayers = 100;
    public String levelName;
    public boolean reducedDebug;

    @Override
    public int getProtocol() {
        return 1;
    }

    @Override
    public void decode(ByteBuf byteBuf)
    {
        // NOOP
    }

    @Override
    public void encode(ByteBuf byteBuf)
    {
        byteBuf.writeInt(entityId);
        byteBuf.writeByte(gamemode);
        byteBuf.writeByte(dimension);
        byteBuf.writeByte(difficulty);
        byteBuf.writeByte(maxPlayers);
        ProtocolUtils.writeString(byteBuf, "default");
        byteBuf.writeBoolean(reducedDebug);
    }

    @Override
    public void handle(PacketHandler packetHandler)
    {

    }
}
