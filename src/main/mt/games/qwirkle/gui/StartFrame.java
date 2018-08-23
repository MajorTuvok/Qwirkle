package mt.games.qwirkle.gui;

import mt.games.qwirkle.helper.Constants;

import javax.swing.*;

public class StartFrame extends JFrame {
    private JButton mCreateGame;
    private JButton mJoinGame;
    private JButton mSinglePlayer;
    private JPanel mRootPane;

    public StartFrame() {
        super(Constants.NAME_START_FRAME);
        setSize(Constants.DEFAULT_FRAME_WIDTH, Constants.DEFAULT_FRAME_HEIGHT);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(mRootPane);
        mCreateGame.addActionListener(e -> new MultiplayerDialog(this, getWidth(), getHeight()).setVisible(true));
    }
}
