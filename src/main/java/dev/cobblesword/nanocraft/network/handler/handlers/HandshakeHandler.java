package dev.cobblesword.nanocraft.network.handler.handlers;

import dev.cobblesword.nanocraft.network.PlayerConnection;
import dev.cobblesword.nanocraft.network.NetworkState;
import dev.cobblesword.nanocraft.network.handler.PacketHandler;
import dev.cobblesword.nanocraft.network.packets.handshake.HandshakePacket;

public class HandshakeHandler extends PacketHandler
{
    private PlayerConnection playerConnection;

    public HandshakeHandler(PlayerConnection playerConnection) {
        this.playerConnection = playerConnection;
    }

    @Override
    public boolean handle(HandshakePacket packet)
    {
        if(packet.status == 1) // Ping
        {

        }
        else if(packet.status == 2) //Login
        {
            this.playerConnection.setPacketHandler(new LoginHandler(this.playerConnection));
            playerConnection.setState(NetworkState.LOGIN);
            return true;
        }
        return false;
    }
}
