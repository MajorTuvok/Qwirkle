package mt.games.qwirkle.backend.obstacles;

import mt.games.qwirkle.gui.IRendable;

import java.awt.*;

public abstract class GamePiece implements IRendable {
    private Color mColour;
    private GamePos mGridPos;

    public GamePiece(Color colour) {
        this.mColour = colour;
        this.mGridPos = new GamePos(0, 0);
    }

    public GamePos getmGridPos() {
        return mGridPos;
    }

    public void setmGridPos(GamePos mGridPos) {
        this.mGridPos = mGridPos;
    }
}
