package mt.games.qwirkle.backend;

import mt.games.qwirkle.backend.obstacles.GamePiece;
import mt.games.qwirkle.backend.obstacles.GamePos;

import java.util.ArrayList;
import java.util.List;

public class ClientGame {
    private List<List<GamePiece>> mGrid;

    public ClientGame() {
        mGrid = new ArrayList<>();
    }

    public void setPiece(GamePiece piece) {
        GamePos pos = piece.getPos();

    }
}
