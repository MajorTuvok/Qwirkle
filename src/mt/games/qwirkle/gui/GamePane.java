package mt.games.qwirkle.gui;

import mt.games.qwirkle.backend.obstacles.*;
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
            row.add(new MinusPiece(color));
            row.add(new MultiplyPiece(color));
            row.add(new DivisionPiece(color));
            row.add(new ToThePowerOfPiece(color));
            row.add(new SquarerootPiece(color));
            mDrawables.add(row);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
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
                row.get(j).render(graphics2D, x + pW * j, y + pH * i, pW, pH);
            }
        }
    }
}
