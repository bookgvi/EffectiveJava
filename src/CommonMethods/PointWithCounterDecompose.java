package CommonMethods;

import java.util.concurrent.atomic.AtomicInteger;

public class PointWithCounterDecompose implements IPoint {
    private final Point point;
    AtomicInteger counter = new AtomicInteger();
    PointWithCounterDecompose(int x, int y) {
        this.point = new Point(x, y);
        this.counter.incrementAndGet();
    }

    public int getX() {
        return point.getX();
    }

    public int getY() {
        return point.getY();
    }

    public void setX(int x) {
        this.point.setX(x);
    }

    public void setY(int y) {
        this.point.setY(y);
    }

    public int getCount() {
        return counter.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PointWithCounterDecompose)) return false;
        PointWithCounterDecompose pwcd = (PointWithCounterDecompose) o;
        return pwcd.point.equals(point);
    }
}
