package dev.cobblesword.nanocraft.network.pipeline.coders;

import dev.cobblesword.nanocraft.common.ProtocolUtils;
import dev.cobblesword.nanocraft.network.packets.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MCEncoder extends MessageToByteEncoder<Packet>
{

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet in, ByteBuf out) throws Exception
    {
        int packetId = in.getProtocol();
        ProtocolUtils.writeVarInt(out, packetId);
        in.encode(out);
    }
}
