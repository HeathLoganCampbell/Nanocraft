package dev.cobblesword.nanocraft.network.handler.handlers;

import dev.cobblesword.nanocraft.network.PlayerConnection;
import dev.cobblesword.nanocraft.network.handler.PacketHandler;
import dev.cobblesword.nanocraft.network.packets.play.movement.PlayerOnGroundPacket;
import dev.cobblesword.nanocraft.network.packets.play.movement.PlayerPositionPacket;
import dev.cobblesword.nanocraft.network.packets.play.player.ChatMessagePacket;
import dev.cobblesword.nanocraft.network.packets.play.player.KeepAlivePacket;

public class PlayHandler extends PacketHandler
{
    private PlayerConnection playerConnection;

    public PlayHandler(PlayerConnection playerConnection) {
        this.playerConnection = playerConnection;
    }

    @Override
    public void handle(PlayerPositionPacket playerPositionPacket)
    {
    }

    @Override
    public void handle(PlayerOnGroundPacket playerOnGroundPacket)
    {

    }

    @Override
    public void handle(ChatMessagePacket chatMessagePacket)
    {
        System.out.println(chatMessagePacket.message);
        this.playerConnection.sendPacket(new ChatMessagePacket(chatMessagePacket.message, (byte) 1));
    }

    @Override
    public void handle(KeepAlivePacket keepAlivePacket)
    {

    }
}
