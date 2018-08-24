package mt.games.qwirkle.backend.obstacles;

import mt.games.qwirkle.resources.FilteredImageResource;
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
        ImageResource res = ResourceManager.INSTANCE.findResourceFor(FilteredImageResource.class, MINUS_PIECE_RES_NAME + getColour().name());
        Image img = res.getImageWrapper().getImage();
        graphics2D.drawImage(img, xPos, yPos, width, height, null);
    }
}
