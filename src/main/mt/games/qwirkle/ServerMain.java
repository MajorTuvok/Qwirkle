package mt.games.qwirkle;

import mt.games.qwirkle.helper.Constants;
import mt.games.qwirkle.helper.ResourceHelper;
import mt.games.qwirkle.network.IConnection;
import mt.games.qwirkle.network.IConnector;
import mt.games.qwirkle.network.server.ServerInetSocketConfig;
import mt.games.qwirkle.network.server.ServerNetConnector;
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
        try {
            handleConnect(new ServerNetConnector(), ServerInetSocketConfig.create(Constants.GAME_PORT)).close();
        } catch (Exception e) {
            System.err.println("Server failed to connect!");
            e.printStackTrace();
        }
    }

    private static <T, D> IConnection handleConnect(IConnector<T, D> connector, T data) throws Exception {
        System.out.println("Prepare Server connect");
        D intermediateRes = connector.prepareConnect(data);
        System.out.println("Try Server connect");
        IConnection res = connector.tryConnect(data, intermediateRes);
        System.out.println("Server connected successfully");
        return res;
    }
}
