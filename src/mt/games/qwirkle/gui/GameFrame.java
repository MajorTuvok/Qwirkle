package mt.games.qwirkle.gui;

import mt.games.qwirkle.backend.ClientGame;
import mt.games.qwirkle.helper.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame {
    private GamePane mPane;
    private ClientGame mGame;

    public GameFrame(ClientGame game, int width, int height) throws HeadlessException {
        super(Constants.NAME_GAME_FRAME);
        setSize(width, height);
        mPane = new GamePane();
        mGame = game;
        setContentPane(mPane);
    }

    public ClientGame getGame() {
        return mGame;
    }

    private class GameWindowEventListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
        }
    }
}
