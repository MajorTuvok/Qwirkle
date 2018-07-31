package mt.games.qwirkle.gui;

import mt.games.qwirkle.helper.Constants;

import javax.swing.*;

public class StartFrame extends JFrame {
    private JButton mCreateGame;
    private JButton mJoinGame;
    private JButton mSinglePlayer;

    public StartFrame() {
        super(Constants.NAME_START_FRAME);
        setSize(Constants.DEFAULT_FRAME_WIDTH, Constants.DEFAULT_FRAME_HEIGHT);
        setContentPane(rootPane);
    }
}
