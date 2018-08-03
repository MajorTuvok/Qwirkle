package mt.games.qwirkle.backend.obstacles;

import mt.games.qwirkle.resources.ImageResource;
import mt.games.qwirkle.resources.ResourceManager;

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
        graphics2D.drawImage(((ImageResource) ResourceManager.INSTANCE.findResourceFor("minus_" + getColour().name())).getImage().getImage(), xPos, yPos, width, height, null);
    }
}
