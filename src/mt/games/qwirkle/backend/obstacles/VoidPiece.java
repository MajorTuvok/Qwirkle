package mt.games.qwirkle.backend.obstacles;

import java.awt.*;

public class VoidPiece extends GamePiece {
    public VoidPiece(GamePos pos) {
        super(Color.BLACK, pos);
    }

    @Override
    public void render(Graphics2D graphics2D, int xPos, int yPos, int width, int height) {

    }
}
