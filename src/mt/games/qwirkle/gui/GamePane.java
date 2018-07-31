package mt.games.qwirkle.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePane extends JPanel {
    private List<IRendable> mDrawables;

    public GamePane() {
        super(true);
        mDrawables = new ArrayList<>();
    }

    @Override
    public void paint(Graphics g) {

    }
}
