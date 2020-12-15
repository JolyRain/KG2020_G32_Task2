package ellipseDrawers;

import lineDrawers.LineDrawer;
import lineDrawers.WuLineDrawer;
import pixelDrawers.PixelDrawer;

import java.awt.*;
import java.util.Map;

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
                delta += -8 * squareA * (y - 1) + 4 * squareB * (2 * x + 3);
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
                delta += -8 * squareB * (x + 1) + 4 * squareA * (2 * y + 3);
                x++;
            }
        }
    }

    private double withoutPeriod(double angle) {
        while (angle > 360) {
            angle -= 180;
        }
        return angle;
    }

    private void drawEllipsePoints(int centerX, int centerY, int x, int y, int startAngle, int arcAngle, Color color) {
        double angle = withoutPeriod(Math.toDegrees(Math.atan2(y, x)));
        int endAngle = startAngle + arcAngle;

        // 0 - 90
        if (180 - angle >= startAngle && 180 - angle <= endAngle) {
            pixelDrawer.drawPixel(centerX - x, centerY - y, color);
        }

        // 90 - 180
        if (angle >= startAngle && angle <= endAngle) {
            pixelDrawer.drawPixel(centerX + x, centerY - y, color);
        }

        // 180 - 270
        if (360 - angle >= startAngle && 360 - angle <= endAngle) {
            pixelDrawer.drawPixel(centerX + x, centerY + y, color);
        }

        // 270 - 360
        if (angle + 180 >= startAngle && angle + 180 <= endAngle) {
            pixelDrawer.drawPixel(centerX - x, centerY + y, color);
        }
    }

    @Override
    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle, Color color) {
//        fillArcLevel(x, y, width - 2, height, startAngle, arcAngle, color);
//        fillArcLevel(x, y, width, height - 2, startAngle, arcAngle, color);
//        fillArcLevel(x, y, width - 2, height - 2, startAngle, arcAngle, color);
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
