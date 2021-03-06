package mt.games.qwirkle.backend.obstacles;

import mt.games.qwirkle.resources.FilteredImageResource;
import mt.games.qwirkle.resources.ResourceManager;

import java.awt.*;

public class ToThePowerOfPiece extends GamePiece {
    public ToThePowerOfPiece(ValidColour colour) {
        super(colour);
    }

    public ToThePowerOfPiece(ValidColour colour, GamePos pos) {
        super(colour, pos);
    }

    @Override
    public void render(Graphics2D graphics2D, int xPos, int yPos, int width, int height) {
        graphics2D.drawImage(ResourceManager.INSTANCE.findResourceFor(FilteredImageResource.class, TO_THE_POWER_OF_PIECE_RES_NAME + getColour().name()).getImageWrapper().getImage(), xPos, yPos, width, height, null);
    }
}
