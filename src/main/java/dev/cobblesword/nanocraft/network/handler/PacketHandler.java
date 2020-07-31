package dev.cobblesword.nanocraft.network.handler;

import dev.cobblesword.nanocraft.network.packets.Packet;
import dev.cobblesword.nanocraft.network.packets.handshake.HandshakePacket;
import dev.cobblesword.nanocraft.network.packets.login.LoginPacket;
import dev.cobblesword.nanocraft.network.packets.play.movement.PlayerOnGroundPacket;
import dev.cobblesword.nanocraft.network.packets.play.movement.PlayerPositionPacket;
import dev.cobblesword.nanocraft.network.packets.play.player.ChatMessagePacket;
import dev.cobblesword.nanocraft.network.packets.play.player.KeepAlivePacket;

public class PacketHandler
{
    public void handleGenericFTW(Packet packet)
    {

    }

    public void connected()
    {

    }

    public void disconnected()
    {

    }

    public boolean handle(HandshakePacket packet)
    {
        return false;
    }

    public boolean handle(LoginPacket packet)
    {
        return false;
    }

    public void handle(PlayerPositionPacket playerPositionPacket)
    {

    }

    public void handle(PlayerOnGroundPacket playerOnGroundPacket)
    {

    }

    public void handle(ChatMessagePacket chatMessagePacket)
    {
    }

    public void handle(KeepAlivePacket keepAlivePacket)
    {

    }
}
