package mt.games.qwirkle.backend.obstacles;

import mt.games.qwirkle.gui.IRenderable;

import java.awt.*;

public abstract class GamePiece implements IRenderable {
    private Color mColour;
    private GamePos mGridPos;

    public GamePiece(Color colour) {
        this(colour, new GamePos(0, 0));
    }

    public GamePiece(Color colour, GamePos pos) {
        this.mColour = colour;
        this.mGridPos = pos;
    }

    public void setGridPos(GamePos mGridPos) {
        this.mGridPos = mGridPos;
    }

    @Override
    public GamePos getPos() {
        return mGridPos;
    }

    public Color getColour() {
        return mColour;
    }

    public void setColour(Color colour) {
        mColour = colour;
    }
}
