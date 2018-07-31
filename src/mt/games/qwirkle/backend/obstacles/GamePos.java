package mt.games.qwirkle.backend.obstacles;

public class GamePos {
    private int x;
    private int y;

    public GamePos(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public GamePos(int y) {
        this.y = y;

    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
