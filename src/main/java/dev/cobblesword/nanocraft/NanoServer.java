package dev.cobblesword.nanocraft;

import dev.cobblesword.nanocraft.network.ConnectionManager;

public class NanoServer implements Runnable
{
    public static final int PORT = 25565;

    private ConnectionManager connectionManager;

    public NanoServer()
    {

    }

    public void start()
    {
        this.connectionManager = new ConnectionManager();
        this.connectionManager.start();

        this.run();

        connectionManager.shutdown();
    }

    @Override
    public void run()
    {
        while(true)
        {
            // TO DO server Ticker for scheduling tasks and keep alive

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
