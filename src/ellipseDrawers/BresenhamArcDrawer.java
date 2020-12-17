package ellipseDrawers;

import lineDrawers.LineDrawer;
import lineDrawers.WuLineDrawer;
import pixelDrawers.PixelDrawer;

import java.awt.*;

public class BresenhamArcDrawer implements ArcDrawer {
    private PixelDrawer pixelDrawer;
    private LineDrawer lineDrawer;

    public BresenhamArcDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
        lineDrawer = new WuLineDrawer(pixelDrawer);
    }

    @Override
    public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle, Color color) {
        int a = width / 2;
        int b = height / 2;
        int centerX = x;
        int centerY = y;
        x = 0;
        y = b;
        int squareA = a * a;
        int squareB = b * b;
        int delta = 4 * squareB * ((x + 1) * (x + 1)) + squareA * ((2 * y - 1) * (2 * y - 1)) - 4 * squareA * squareB;

        while (squareA * (2 * y - 1) > 2 * squareB * (x + 1)) {
            drawEllipsePoints(centerX, centerY, x, y, startAngle, arcAngle, color);
            if (delta < 0) {
                x++;
                delta += 4 * squareB * (2 * x + 3);
            } else {
                x++;
                delta = delta -8 * squareA * (y - 1) + 4 * squareB * (2 * x + 3);
                y--;
            }
        }
        delta = squareB * ((2 * x + 1) * (2 * x + 1)) + 4 * squareA * ((y + 1) * (y + 1)) - 4 * squareA * squareB;
        while (y + 1 != 0) {
            drawEllipsePoints(centerX, centerY, x, y, startAngle, arcAngle, color);
            if (delta < 0) {
                y--;
                delta += 4 * squareA * (2 * y + 3);
            } else {
                y--;
                delta = delta -8 * squareB * (x + 1) + 4 * squareA * (2 * y + 3);
                x++;
            }
        }
    }

    private int withoutPeriod(int angle) {
        while (angle > 360) {
            angle -= 360;
        }
        return angle;
    }

    private void drawEllipsePoints(int centerX, int centerY, int x, int y, int startAngle, int arcAngle, Color color) {
        double angle = Math.toDegrees(Math.atan2(y, x));
        startAngle = withoutPeriod(startAngle);
        arcAngle = withoutPeriod(arcAngle);
        int endAngle = withoutPeriod(startAngle + arcAngle);

        boolean mustReverse = false;

        if (endAngle < startAngle) {
            int buf = endAngle;
            endAngle = startAngle;
            startAngle = buf;
            mustReverse = true;
        }
        // 0 - 90
        if (reverse(180 - angle >= startAngle && 180 - angle <= endAngle, mustReverse)) {

            pixelDrawer.drawPixel(centerX - x, centerY - y, color);
        }

        // 90 - 180
        if (reverse(angle >= startAngle && angle <= endAngle, mustReverse)) {

            pixelDrawer.drawPixel(centerX + x, centerY - y, color);
        }

        // 180 - 270
        if (reverse(360 - angle >= startAngle && 360 - angle <= endAngle, mustReverse)) {

            pixelDrawer.drawPixel(centerX + x, centerY + y, color);
        }

        // 270 - 360
        if (reverse(angle + 180 >= startAngle && angle + 180 <= endAngle, mustReverse)) {

            pixelDrawer.drawPixel(centerX - x, centerY + y, color);
        }
    }
    private boolean equals(double angle1, double angle2) {
        return Math.abs(angle1 - angle2) < 1e-1;
    }

    private boolean reverse(boolean predicate, boolean reverse) {
        if (reverse) return !predicate;
        else return predicate;
    }

    @Override
    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle, Color color) {
        fillArcLevel(x, y, width - 2, height, startAngle, arcAngle, color);
        fillArcLevel(x, y, width, height - 2, startAngle, arcAngle, color);
        fillArcLevel(x, y, width - 2, height - 2, startAngle, arcAngle, color);
    }

    private void fillArcLevel(int x, int y, int width, int height, int startAngle, int arcAngle, Color color) {
        int a = width / 2;
        int b = height / 2;
        int centerX = x;
        int centerY = y;
        x = 0;
        y = b;
        int squareA = a * a;
        int squareB = b * b;
        int delta = 4 * squareB * ((x + 1) * (x + 1)) + squareA * ((2 * y - 1) * (2 * y - 1)) - 4 * squareA * squareB;

        while (squareA * (2 * y - 1) > 2 * squareB * (x + 1)) {
            fillEllipsePoints(centerX, centerY, x, y, startAngle, arcAngle, color);
            if (delta < 0) {
                x++;
                delta += 4 * squareB * (2 * x + 3);
            } else {
                x++;
                delta += -8 * squareA * (y - 1) + 4 * squareB * (2 * x + 3);
                y--;
            }
        }
        delta = squareB * ((2 * x + 1) * (2 * x + 1)) + 4 * squareA * ((y + 1) * (y + 1)) - 4 * squareA * squareB;
        while (y + 1 != 0) {
            fillEllipsePoints(centerX, centerY, x, y, startAngle, arcAngle, color);

            if (delta < 0) {
                y--;
                delta += 4 * squareA * (2 * y + 3);
            } else {
                y--;
                delta += -8 * squareB * (x + 1) + 4 * squareA * (2 * y + 3);
                x++;
            }
        }
    }


    private void fillEllipsePoints(int centerX, int centerY, int x, int y, int startAngle, int arcAngle, Color color) {
        double angle = Math.toDegrees(Math.atan2(y, x));
        int endAngle = startAngle + arcAngle;

        // 0 - 90
        if (180 - angle >= startAngle && 180 - angle <= endAngle) {
            lineDrawer.drawLine(centerX, centerY, centerX - x, centerY - y, color);
        }

        // 90 - 180
        if (angle >= startAngle && angle <= endAngle) {
            lineDrawer.drawLine(centerX, centerY, centerX + x, centerY - y, color);
        }

        // 180 - 270
        if (360 - angle >= startAngle && 360 - angle <= endAngle) {
            lineDrawer.drawLine(centerX, centerY, centerX + x, centerY + y, color);
        }

        // 270 - 360
        if (angle + 180 >= startAngle && angle + 180 <= endAngle) {
            lineDrawer.drawLine(centerX, centerY, centerX - x, centerY + y, color);
        }
    }
}
