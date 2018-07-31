package mt.games.qwirkle;

import mt.games.qwirkle.gui.StartFrame;
import mt.games.qwirkle.helper.ResourceHelper;
import mt.games.qwirkle.resources.PropertiesResource;
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
        ResourceManager mang = ResourceManager.INSTANCE;
        mang.addResource("gameCfg", new PropertiesResource("gameDefaults.properties", ResourceHelper.INTERNAL_SUPPLIER));
    }

    private static void ResourceTest() {
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