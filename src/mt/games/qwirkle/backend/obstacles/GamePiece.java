package mt.games.qwirkle.backend.obstacles;

import mt.games.qwirkle.gui.IRenderable;

public abstract class GamePiece implements IRenderable {
    private ValidColour mColour;
    private GamePos mGridPos;

    public GamePiece(ValidColour colour) {
        this(colour, new GamePos(0, 0));
    }

    public GamePiece(ValidColour colour, GamePos pos) {
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

    public ValidColour getColour() {
        return mColour;
    }

    public void setColour(ValidColour colour) {
        mColour = colour;
    }
}
