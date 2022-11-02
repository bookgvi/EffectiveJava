package CommonMethods;

import java.awt.*;

public class ColorPointDecompose implements IPoint {
    private final Point point;
    private Color color;

    ColorPointDecompose(int x, int y, Color color) {
        this.point = new Point(x, y);
        this.color = color;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ColorPointDecompose)) return false;
        ColorPointDecompose cp = (ColorPointDecompose) o;
        return cp.point.equals(point) && cp.getColor().equals(color);
    }
}
