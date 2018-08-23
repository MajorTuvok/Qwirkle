package mt.games.qwirkle.backend.obstacles;

import mt.games.qwirkle.resources.ImageResource;
import mt.games.qwirkle.resources.ResourceManager;

import java.awt.*;

public class DivisionPiece extends GamePiece {
    public DivisionPiece(ValidColour colour) {
        super(colour);
    }

    public DivisionPiece(ValidColour colour, GamePos pos) {
        super(colour, pos);
    }

    @Override
    public void render(Graphics2D graphics2D, int xPos, int yPos, int width, int height) {
        graphics2D.drawImage(((ImageResource) ResourceManager.INSTANCE.findResourceFor("division_" + getColour().name())).getImageWrapper().getImage(), xPos, yPos, width, height, null);
    }

}
