package CommonMethods;

import java.awt.*;

public class ColorPoint extends Point implements IPoint {
    private Color color;
    ColorPoint(int x, int y, Color color) {
        super(x, y);
        this.color = color;
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
        if (!(o instanceof Point)) return false;
        if(!(o instanceof ColorPoint)) return o.equals(this);
//        if (o == null || o.getClass() != getClass()) return false;
        return super.equals(o) && ((ColorPoint) o).color == getColor();
    }

    @Override
    public int hashCode() {
        int res = super.hashCode();
        res = (res << 5) - res + color.hashCode();
        return res;
    }
}
