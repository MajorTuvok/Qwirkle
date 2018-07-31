package mt.games.qwirkle.gui;

import mt.games.qwirkle.helper.Constants;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private GamePane mPane;

    public GameFrame(int width, int height) throws HeadlessException {
        super(Constants.NAME_GAME_FRAME);
        setSize(width, height);
        setContentPane(mPane);
    }
}
