package mt.games.qwirkle;

import mt.games.qwirkle.helper.ResourceHelper;
import mt.games.qwirkle.resources.PropertiesResource;
import mt.games.qwirkle.resources.ResourceManager;

public class ServerMain {
    public static void main(String[] args) {
        registerResources();
        ResourceManager.INSTANCE.load();
    }

    static void registerResources() {
        ResourceManager mang = ResourceManager.INSTANCE;
        mang.addResource("gameCfg", new PropertiesResource("gameDefaults.properties", ResourceHelper.INTERNAL_SUPPLIER));
    }

    private static void connectTest() {
        /*try {
            new ServerNetConnector().connect(new ServerInetSocketConfig(Constants.GAME_PORT), new IConnectCallbacks<ServerInetSocketConfig>() {
                @Override
                public void onPrepareConnect(IConnector<ServerInetSocketConfig> connector) {
                    System.out.println("Server prepare connect");
                }

                @Override
                public void onTryConnect(IConnector<ServerInetSocketConfig> connector) {
                    System.out.println("Server try connect");
                }

                @Override
                public void onConnected(IConnector<ServerInetSocketConfig> connector) {
                    System.out.println("Server connected");
                }

                @Override
                public void onConnectFailed(IConnector<ServerInetSocketConfig> connector, Exception cause) {
                    System.err.println("Server failed to connect because of " + cause.getClass().getName());
                }
            }).close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
