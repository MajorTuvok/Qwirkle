package mt.games.qwirkle.gui;

import mt.games.qwirkle.helper.Constants;

import javax.swing.*;
import java.awt.*;

public class MultiplayerDialog extends JDialog {
    private JPanel mRootPane;
    private JButton mCancelButton;
    private JButton mOkButton;

    public MultiplayerDialog(Frame owner, int width, int height) {
        super(owner, Constants.NAME_MULTIPLAYER_DIALOG);
        setSize(Math.max(width, 0), Math.max(height, 0));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        mOkButton.addActionListener(e -> {
            new GameFrame(null, getWidth(), getHeight()).setVisible(true);
            setVisible(false);
            dispose();
        });
        mCancelButton.addActionListener(e -> dispose());
        setContentPane(mRootPane);
    }
}
