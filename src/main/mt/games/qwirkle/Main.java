package mt.games.qwirkle;

import mt.games.qwirkle.backend.obstacles.ValidColour;
import mt.games.qwirkle.gui.StartFrame;
import mt.games.qwirkle.helper.ResourceHelper;
import mt.games.qwirkle.resources.FilteredImageResource;
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
        for (ValidColour color : ValidColour.values()) {
            if (color == ValidColour.NONE) continue;
            mang.addResource("plus_" + color.name(), new FilteredImageResource(color.getColor(), "plus.png", ResourceHelper.INTERNAL_SUPPLIER));
            mang.addResource("minus_" + color.name(), new FilteredImageResource(color.getColor(), "minus.png", ResourceHelper.INTERNAL_SUPPLIER));
            mang.addResource("multiply_" + color.name(), new FilteredImageResource(color.getColor(), "multiply.png", ResourceHelper.INTERNAL_SUPPLIER));
            mang.addResource("division_" + color.name(), new FilteredImageResource(color.getColor(), "division.png", ResourceHelper.INTERNAL_SUPPLIER));
            mang.addResource("sqrt_" + color.name(), new FilteredImageResource(color.getColor(), "sqrt.png", ResourceHelper.INTERNAL_SUPPLIER));
            mang.addResource("x^2_" + color.name(), new FilteredImageResource(color.getColor(), "x^2.png", ResourceHelper.INTERNAL_SUPPLIER));
        }
    }

    private static void connectTest() {
        /*try {
            new ClientNetConnector().connect(new ClientInetSocketConfig("localhost", Constants.GAME_PORT), new IConnectCallbacks<ClientInetSocketConfig>() {
                @Override
                public void onPrepareConnect(IConnector<ClientInetSocketConfig> connector) {
                    System.out.println("Client prepare connect");
                }

                @Override
                public void onTryConnect(IConnector<ClientInetSocketConfig> connector) {
                    System.out.println("Client try connect");
                }

                @Override
                public void onConnected(IConnector<ClientInetSocketConfig> connector) {
                    System.out.println("Client connected");
                }

                @Override
                public void onConnectFailed(IConnector<ClientInetSocketConfig> connector, Exception cause) {
                    System.err.println("Client failed to connect because of " + cause.getClass().getName());
                }
            }).close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
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