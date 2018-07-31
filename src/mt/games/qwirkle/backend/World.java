package mt.games.qwirkle.backend;

import mt.games.qwirkle.backend.obstacles.GamePiece;
import mt.games.qwirkle.backend.obstacles.GamePos;

import java.util.ArrayList;
import java.util.List;

public class World {
    private List<List<GamePiece>> mGrid;

    public World() {
        mGrid = new ArrayList<>();
    }

    public void setPiece(GamePiece piece) {
        GamePos pos = piece.getPos();
        assert pos.getX() > 0 && pos.getY() > 0 : "Positions can never be negative!";
        if (pos.getY() < getGrid().size()) {
            List<GamePiece> row = getGrid().get(pos.getY());
        } else {

        }
    }

    protected List<List<GamePiece>> getGrid() {
        return mGrid;
    }
}
