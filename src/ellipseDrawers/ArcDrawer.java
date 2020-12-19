package ellipseDrawers;

import java.awt.*;

import static java.lang.Math.PI;

public interface ArcDrawer {
//    double EPSILON = 0.01;

    default boolean isPointInside(int x, int y, int width, int height, double startAngle, double arcAngle) {
        startAngle = withoutPeriod(startAngle);
        arcAngle = withoutPeriod(arcAngle);

        double endAngle = withoutPeriod(startAngle + arcAngle);
        double minAngle = Math.min(startAngle, endAngle);
        double maxAngle = Math.max(startAngle, endAngle);
        double deltaAngle = (maxAngle - minAngle);

        int a = width / 2;
        int b = height / 2;

        boolean mustReverse = false;

        if (endAngle < startAngle) {
            double buf = endAngle;
            endAngle = startAngle;
            startAngle = buf;
            mustReverse = true;
        }

//        if (deltaAngle < EPSILON)
//            return false;
//        if (Math.abs(deltaAngle - 2 * PI) < EPSILON)
//            return true;
        double angle = withoutPeriod(Math.atan2(y / (double) b, x / (double) a));
        return reverse(minAngle < angle
                && angle < maxAngle, mustReverse);
//                || minAngle <= angle + PI * 2
//                && angle + PI * 2 <= minAngle;
    }

    default double withoutPeriod(double angel) {
        return angel != 2 * PI ? angel % (2 * PI) : 2 * PI;
    }

    default boolean reverse(boolean predicate, boolean reverse) {
        return reverse != predicate;
    }

    void drawArc(int centerX, int centerY, int width, int height, double startAngle, double arcAngle, Color color);

}
