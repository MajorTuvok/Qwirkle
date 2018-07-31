package mt.games.qwirkle.backend.obstacles;

public class GamePos {
    private int x;
    private int y;

    public GamePos(int x, int y) {
        setX(x);
        setY(y);
    }

    public GamePos(GamePos pos) {
        this.x = pos.getX();
        this.y = pos.getY();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = Math.max(x, 0);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = Math.max(y, 0);
    }

    @Override
    public int hashCode() {
        int result = getX();
        result = 31 * result + getY();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GamePos)) return false;

        GamePos gamePos = (GamePos) o;

        if (getX() != gamePos.getX()) return false;
        return getY() == gamePos.getY();
    }

    @Override
    public String toString() {
        return "GamePos{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
