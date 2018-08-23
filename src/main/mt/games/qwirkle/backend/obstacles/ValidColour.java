package mt.games.qwirkle.backend.obstacles;

import java.awt.*;

public enum ValidColour {
    TURQUOISE(new Color(255, 0, 255)),
    AQUAMARINE(new Color(255, 0, 255)),
    KARMINRED(new Color(255, 0, 255)),
    CITRONYELLOW(new Color(0, 255, 255)),
    SAP_GREEN(new Color(0, 255, 255)),
    NONE(new Color(0, 0, 0)),
    MEDIUM_ORCHID_1(new Color(255, 255, 0));
    private Color mColor;

    ValidColour(Color color) {
        mColor = color;
    }

    public Color getColor() {
        return mColor;
    }

    public void setColor(Color color) {
        mColor = color;
    }
}
