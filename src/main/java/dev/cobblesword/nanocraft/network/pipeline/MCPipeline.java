package dev.cobblesword.nanocraft.network.pipeline;

import dev.cobblesword.nanocraft.network.PlayerConnection;
import dev.cobblesword.nanocraft.network.pipeline.coders.MCDecoder;
import dev.cobblesword.nanocraft.network.pipeline.coders.MCEncoder;
import dev.cobblesword.nanocraft.network.pipeline.encapsulation.Decapsulator;
import dev.cobblesword.nanocraft.network.pipeline.encapsulation.Encapsulator;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class MCPipeline extends ChannelInitializer<SocketChannel>
{
    protected void initChannel(SocketChannel channel) throws Exception
    {
        try {
            channel.config().setOption(ChannelOption.TCP_NODELAY, true);
        } catch (ChannelException channelexception) {
            ;
        }

        channel.pipeline()
                .addLast("timer", new ReadTimeoutHandler(30))

                .addLast("decapsulator", new Decapsulator())
                .addLast("encapsulator", new Encapsulator())

                .addLast("decoder", new MCDecoder())
                .addLast("encoder", new MCEncoder())

                .addLast("handler", new PlayerConnection(channel));
    }
}
