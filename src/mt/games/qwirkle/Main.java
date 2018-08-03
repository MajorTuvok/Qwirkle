package mt.games.qwirkle;

import mt.games.qwirkle.gui.StartFrame;
import mt.games.qwirkle.helper.Constants;
import mt.games.qwirkle.helper.ResourceHelper;
import mt.games.qwirkle.network.IConnectCallbacks;
import mt.games.qwirkle.network.IConnector;
import mt.games.qwirkle.network.client.ClientInetSocketConfig;
import mt.games.qwirkle.network.client.ClientNetConnector;
import mt.games.qwirkle.resources.ResourceManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class Main {
    public static void main(String[] args) {
        registerResources();
        ResourceManager.INSTANCE.load();
        new StartFrame().setVisible(true);
    }

    private static void registerResources() {
        ServerMain.registerResources();
        ResourceManager mang = ResourceManager.INSTANCE;
    }

    private static void connectTest() {
        try {
            new ClientNetConnector().apply(new ClientInetSocketConfig("localhost", Constants.GAME_PORT), new IConnectCallbacks() {
                @Override
                public void onPrepareConnect(IConnector<?> connector) {
                    System.out.println("Client prepare connect");
                }

                @Override
                public void onTryConnect(IConnector<?> connector) {
                    System.out.println("Client try connect");
                }

                @Override
                public void onConnected(IConnector<?> connector) {
                    System.out.println("Client connected");
                }

                @Override
                public void onConnectFailed(IConnector<?> connector, Exception cause) {
                    System.err.println("Client failed to connect because of " + cause.getClass().getName());
                }
            }).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void resourceTest() {
        InputStream stream = ResourceHelper.getInternalStream("gameDefaults.properties");
        if (stream != null) {
            LineNumberReader reader = new LineNumberReader(new InputStreamReader(stream));
            try {
                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}