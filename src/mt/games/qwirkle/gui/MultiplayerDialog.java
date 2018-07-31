package mt.games.qwirkle.gui;

import mt.games.qwirkle.helper.Constants;

import javax.swing.*;
import java.awt.*;

public class MultiplayerDialog extends JDialog {

    public MultiplayerDialog(Frame owner, int width, int height) {
        super(owner, Constants.NAME_MULTIPLAYER_DIALOG);
        setSize(width, height);
    }
}
