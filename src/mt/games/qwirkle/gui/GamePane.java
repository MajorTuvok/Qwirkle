package mt.games.qwirkle.gui;

import com.sun.istack.internal.NotNull;
import mt.games.qwirkle.backend.obstacles.*;
import mt.games.qwirkle.helper.Constants;
import mt.games.qwirkle.helper.MathHelper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class GamePane extends JPanel {
    private RenderCache mCache;
    public GamePane() {
        super(true);
        mCache = new RenderCache();
        inferTestPieces();
    }

    public void notifyDataSetChanged(List<List<IRenderable>> pieces) {
        mCache.notifyDataSetChanged(pieces);
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        mCache.evaluatePieceSize();
    }

    @Override
    protected void paintComponent(Graphics g) {
        assert g instanceof Graphics2D : "Expected 2D Graphics Object!";
        Graphics2D graphics2D = (Graphics2D) g;
        int x = getX();
        int y = getY();
        int size = mCache.getPieceSize();
        for (List<IRenderable> row : mCache.get()) {
            for (IRenderable obj : row) {
                GamePos pos = obj.getPos();
                int xPos = x + (pos.getX() - mCache.getLowestX()) * size;
                int yPos = y + (pos.getY() - mCache.getLowestY()) * size;
                obj.render(graphics2D, xPos, yPos, size, size);
            }
        }
    }

    private void inferTestPieces() {
        List<List<IRenderable>> testList = new ArrayList<>();
        int count = 0;
        for (ValidColour color : ValidColour.values()) {
            if (color == ValidColour.NONE) {
                continue;
            }
            List<IRenderable> row = new ArrayList<>();
            row.add(new PlusPiece(color, new GamePos(0, count)));
            row.add(new MinusPiece(color, new GamePos(1, count)));
            row.add(new MultiplyPiece(color, new GamePos(2, count)));
            row.add(new DivisionPiece(color, new GamePos(3, count)));
            row.add(new ToThePowerOfPiece(color, new GamePos(4, count)));
            row.add(new SquarerootPiece(color, new GamePos(5, count)));
            testList.add(row);
            ++count;
        }
        notifyDataSetChanged(testList);
    }

    private final class RenderCache implements Supplier<List<List<IRenderable>>> {
        private List<List<IRenderable>> mDrawables;
        private int mHighestX;
        private int mHighestY;
        private int mLowestX;
        private int mLowestY;
        private int mSize;

        private RenderCache() {
            mDrawables = new ArrayList<>();
        }

        public int getLowestY() {
            return mLowestY;
        }

        public int getHighestY() {
            return mHighestY;
        }

        public int getLowestX() {
            return mLowestX;
        }

        public int getHighestX() {
            return mHighestX;
        }

        private int getPieceSize() {
            if (mSize <= 0) {
                evaluatePieceSize();
            }
            return mSize;
        }

        @Override
        @NotNull
        public List<List<IRenderable>> get() {
            return mDrawables;
        }

        private void notifyDataSetChanged(List<List<IRenderable>> pieces) {
            for (List<IRenderable> row : mDrawables) {
                row.clear();
            }
            mDrawables.clear();
            resetCache();
            for (List<IRenderable> row : pieces) {
                List<IRenderable> rowCopy = new ArrayList<>(row.size());
                for (IRenderable renderable : row) {
                    rowCopy.add(renderable);
                    updateXYCache(renderable.getPos());
                }
                mDrawables.add(rowCopy);
            }
            evaluatePieceSize();
        }

        private void evaluatePieceSize() {
            if (needsXYUpdate()) {
                reevaluateXYBounds();
            }
            Dimension dim = getSize();
            double h = dim.height;
            double w = dim.width;
            int maxRowWidth = mHighestX - mLowestX + 1;
            int maxRowHeight = mHighestY - mLowestY + 1;
            int pH = (int) Math.round(MathHelper.clamp(h / (double) maxRowHeight, Constants.MIN_PIECE_HEIGHT, Constants.MAX_PIECE_HEIGHT));
            int pW = (int) Math.round(MathHelper.clamp(w / (double) maxRowWidth, Constants.MIN_PIECE_WIDTH, Constants.MAX_PIECE_WIDTH));
            mSize = Math.min(pH, pW);
        }

        private void reevaluateXYBounds() {
            resetXY();
            for (List<IRenderable> row : mDrawables) {
                for (IRenderable renderable : row) {
                    updateXYCache(renderable.getPos());
                }
            }
        }

        private void updateXYCache(GamePos pos) {
            mLowestX = Math.min(mLowestX, pos.getX());
            mHighestX = Math.max(mHighestX, pos.getX());
            mLowestY = Math.min(mLowestY, pos.getY());
            mHighestY = Math.max(mHighestY, pos.getY());
        }

        private boolean needsXYUpdate() {
            return mLowestX > mHighestX || mLowestY > mHighestY;
        }

        private void resetSize() {
            mSize = -1;
        }

        private void resetXY() {
            mLowestX = Integer.MAX_VALUE;
            mHighestX = Integer.MIN_VALUE;
            mLowestY = Integer.MAX_VALUE;
            mHighestY = Integer.MIN_VALUE;
        }

        private void resetCache() {
            resetSize();
            resetXY();
        }
    }
}
