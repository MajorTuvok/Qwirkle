package mt.games.qwirkle.helper;

public final class MathHelper {

    private MathHelper() {
    }

    public static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(val, max));
    }

    public static float clamp(float val, float min, float max) {
        return Math.max(min, Math.min(val, max));
    }

    public static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(val, max));
    }

    public static double lineMiddle(int pos, int length) {
        return pos + length / 2D;
    }
}
