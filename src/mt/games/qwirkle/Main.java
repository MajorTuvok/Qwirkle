package mt.games.qwirkle;

import mt.games.qwirkle.gui.StartFrame;
import mt.games.qwirkle.helper.ResourceHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class Main {
    public static void main(String[] args) {
        new StartFrame().setVisible(true);

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