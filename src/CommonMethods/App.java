package CommonMethods;

import java.awt.*;
import java.util.Set;

public class App {
    private static final Set<IPoint> setOfPoints = Set.of(
            new Point(-1, 0), new Point(0, 1),
            new Point(1, 0), new Point(0, -1),
            new Point(0, 0)
    );

    private static boolean isPointBelongTheSet(IPoint p) {
        return setOfPoints.contains(p);
    }

    public static void main(String[] args) {
        IPoint p = new Point(1, 0);

        IPoint pwc = new PointWithCounter(1, 0);
        IPoint cp1 = new ColorPoint(1, 0, Color.RED);
        IPoint cp2 = new ColorPoint(1, 0, Color.BLUE);
        IPoint cp3 = new ColorPoint(1, 0, Color.BLUE);
        boolean cp1_p = cp1.equals(p);
        boolean p_cp2 = p.equals(cp2);
        boolean cp1_cp2 = cp1.equals(cp2);
        boolean cp2_cp3 = cp2.equals(cp3);

        boolean belongToSet = isPointBelongTheSet(p);
        boolean belongToSet2 = isPointBelongTheSet(pwc);
        System.out.println(((PointWithCounter) pwc).pointCount());
        System.out.println("FIN");

        IPoint cpDecompose1 = new ColorPointDecompose(1, 0 , Color.YELLOW);
        IPoint cpDecompose2 = new ColorPointDecompose(1, 0 , Color.BLUE);
        IPoint pwcd = new PointWithCounterDecompose(1, 0);
        boolean cpd1_p = cpDecompose1.equals(p);
        boolean p_cpd2 = p.equals(cpDecompose2);
        boolean cpd1_cpd2 = cpDecompose1.equals(cpDecompose2);
        boolean belongToSet3 = isPointBelongTheSet(pwcd);
        boolean pwcd_p = pwcd.equals(p);
    }
}
