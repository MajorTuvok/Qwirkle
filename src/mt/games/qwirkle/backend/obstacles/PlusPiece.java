package mt.games.qwirkle.backend.obstacles;

import java.awt.*;

public class PlusPiece extends GamePiece {
    public PlusPiece(Colours colour) {
        super(colour);
    }

    public PlusPiece(Colours colour, GamePos pos) {
        super(colour, pos);
    }

    @Override
    public void render(Graphics2D graphics2D, int xPos, int yPos, int width, int height) {

    }
}
