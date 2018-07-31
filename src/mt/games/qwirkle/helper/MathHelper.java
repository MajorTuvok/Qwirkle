package mt.games.qwirkle.helper;

public class MathHelper {
    public static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(val, max));
    }

    public static double lineMiddle(int pos, int length) {
        return pos + length / 2D;
    }
}
