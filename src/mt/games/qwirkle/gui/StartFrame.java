package mt.games.qwirkle.gui;

import mt.games.qwirkle.helper.Constants;

import javax.swing.*;

public class StartFrame extends JFrame {
    public StartFrame() {
        super("Choose Game-Mode");
        setSize(Constants.DEFAULT_FRAME_WIDTH, Constants.DEFAULT_FRAME_HEIGHT);
        setContentPane(rootPane);
    }
}
