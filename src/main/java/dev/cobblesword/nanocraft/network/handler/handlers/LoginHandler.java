package dev.cobblesword.nanocraft.network.handler.handlers;

import dev.cobblesword.nanocraft.network.PlayerConnection;
import dev.cobblesword.nanocraft.network.NetworkState;
import dev.cobblesword.nanocraft.network.handler.PacketHandler;
import dev.cobblesword.nanocraft.network.packets.login.LoginPacket;
import dev.cobblesword.nanocraft.network.packets.login.LoginSuccessPacket;
import dev.cobblesword.nanocraft.network.packets.play.ChunkDataPacket;
import dev.cobblesword.nanocraft.network.packets.play.JoinGamePacket;
import dev.cobblesword.nanocraft.network.packets.play.movement.PlayerPositionAndLookPacket;

import java.util.UUID;

public class LoginHandler extends PacketHandler
{
    private PlayerConnection playerConnection;

    public LoginHandler(PlayerConnection playerConnection) {
        this.playerConnection = playerConnection;
    }

    @Override
    public boolean handle(LoginPacket packet)
    {
        LoginSuccessPacket loginSuccessPacket = new LoginSuccessPacket(UUID.randomUUID(), packet.username);
        this.playerConnection.sendPacket(loginSuccessPacket);
        this.playerConnection.setState(NetworkState.PLAY);
        this.playerConnection.setPacketHandler(new PlayHandler(this.playerConnection));
        this.playerConnection.sendPacket(new JoinGamePacket(1, 1, 1, 1, 100, "default", false));
        this.playerConnection.sendPacket(new PlayerPositionAndLookPacket(0, 100, 0, 0, 0, false));

        for (int x = 0; x < 4; x++)
        {
            for (int z = 0; z < 4; z++)
            {
                ChunkDataPacket chunkDataPacket = new ChunkDataPacket();
                chunkDataPacket.chunkX = x;
                chunkDataPacket.chunkZ = z;
                this.playerConnection.sendPacket(chunkDataPacket);
            }
        }


//        this.mcServerHandler.sendPacket(new LoginDisconnectPacket("{\"text\": \"unlucky\" }"));
        return true;
    }
}
