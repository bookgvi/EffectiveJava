package CommonMethods;

import java.util.concurrent.atomic.AtomicInteger;

public class PointWithCounter extends Point implements IPoint {
    private final AtomicInteger counter = new AtomicInteger();

    PointWithCounter(int x, int y) {
        super(x, y);
        counter.incrementAndGet();
    }

    public int pointCount() {
        return counter.get();
    }
}
