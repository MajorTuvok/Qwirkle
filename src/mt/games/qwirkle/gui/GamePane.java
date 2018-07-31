package mt.games.qwirkle.gui;

import mt.games.qwirkle.backend.obstacles.PlusPiece;
import mt.games.qwirkle.backend.obstacles.ValidColour;
import mt.games.qwirkle.helper.MathHelper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePane extends JPanel {
    private List<List<IRenderable>> mDrawables;

    public GamePane() {
        super(true);
        mDrawables = new ArrayList<>();
        for (ValidColour color : ValidColour.values()) {
            if (color == ValidColour.NONE) {
                continue;
            }
            List<IRenderable> row = new ArrayList<>();
            row.add(new PlusPiece(color));
            mDrawables.add(row);
        }
    }

    @Override
    public void paint(Graphics g) {
        if (!(g instanceof Graphics2D)) {
            throw new RuntimeException("Expected 2D Graphics Object!");
        }
        Graphics2D graphics2D = (Graphics2D) g;
        Dimension dim = getSize();
        double h = dim.height;
        double w = dim.width;
        int x = getX();
        int y = getY();
        int pH = (int) Math.round(MathHelper.clamp(h / (double) mDrawables.size(), 32, 128));
        for (int i = 0; i < mDrawables.size(); i++) {
            List<IRenderable> row = mDrawables.get(i);
            int pW = (int) Math.round(MathHelper.clamp(w / (double) row.size(), 32, 128));
            for (int j = 0; j < row.size(); j++) {
                row.get(i).render(graphics2D, x + pW * i, y, pW, pH);
            }
        }
    }
}
