package CommonMethods;

import java.util.Objects;

public class Point implements IPoint {
    private int x;
    private int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
//        if (o == null || o.getClass() != getClass()) return false;
        Point point = (Point) o;
        return point.getX() == getX() && point.getY() == getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
