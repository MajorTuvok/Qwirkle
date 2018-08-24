package mt.games.qwirkle.backend.obstacles;

import mt.games.qwirkle.gui.IRenderable;
import mt.games.qwirkle.helper.Constants;

public abstract class GamePiece implements IRenderable, Constants {
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

    @Override
    public int hashCode() {
        return getColour().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o.getClass().equals(this.getClass()))) return false;

        GamePiece gamePiece = (GamePiece) o;

        return getColour() == gamePiece.getColour();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "mColour=" + mColour +
                ", mGridPos=" + mGridPos +
                '}';
    }
}
