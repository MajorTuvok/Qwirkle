package mt.games.qwirkle.backend.obstacles;

import mt.games.qwirkle.gui.IRenderable;

public abstract class GamePiece implements IRenderable {
    private Colours mColour;
    private GamePos mGridPos;

    public GamePiece(Colours colour) {
        this(colour, new GamePos(0, 0));
    }

    public GamePiece(Colours colour, GamePos pos) {
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

    public Colours getColour() {
        return mColour;
    }

    public void setColour(Colours colour) {
        mColour = colour;
    }
}
