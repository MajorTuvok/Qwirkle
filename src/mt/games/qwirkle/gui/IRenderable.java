package mt.games.qwirkle.gui;

import mt.games.qwirkle.backend.obstacles.GamePos;

import java.awt.*;

public interface IRenderable {
    public void render(Graphics2D graphics2D, int xPos, int yPos, int width, int height);

    public GamePos getPos();
}
