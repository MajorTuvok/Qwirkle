package mt.games.qwirkle;

import mt.games.qwirkle.backend.obstacles.ValidColour;
import mt.games.qwirkle.gui.StartFrame;
import mt.games.qwirkle.helper.Constants;
import mt.games.qwirkle.helper.ResourceHelper;
import mt.games.qwirkle.network.IConnection;
import mt.games.qwirkle.network.IConnector;
import mt.games.qwirkle.network.client.ClientInetSocketConfig;
import mt.games.qwirkle.network.client.ClientNetConnector;
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
        try {
            handleConnect(new ClientNetConnector(), ClientInetSocketConfig.create("localhost", Constants.GAME_PORT)).close();
        } catch (Exception e) {
            System.err.println("Client failed to connect!");
            e.printStackTrace();
        }
    }

    private static <T, D> IConnection handleConnect(IConnector<T, D> connector, T data) throws Exception {
        System.out.println("Prepare Client connect");
        D intermediateRes = connector.prepareConnect(data);
        System.out.println("Try Client connect");
        IConnection res = connector.tryConnect(data, intermediateRes);
        System.out.println("Client connected successfully");
        return res;
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