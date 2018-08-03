package mt.games.qwirkle.backend.obstacles;

import java.awt.*;

public class MinusPiece extends GamePiece {

    public MinusPiece(ValidColour colour) {
        super(colour);
    }

    public MinusPiece(ValidColour colour, GamePos pos) {
        super(colour, pos);
    }

    @Override
    public void render(Graphics2D graphics2D, int xPos, int yPos, int width, int height) {

    }
}
