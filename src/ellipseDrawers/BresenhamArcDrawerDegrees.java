package ellipseDrawers;

import lineDrawers.BresenhamLineDrawer;
import lineDrawers.LineDrawer;
import pixelDrawers.PixelDrawer;

import java.awt.*;

public class BresenhamArcDrawerDegrees implements ArcDrawer {
    private PixelDrawer pixelDrawer;
    private LineDrawer lineDrawer;

    public BresenhamArcDrawerDegrees(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
        lineDrawer = new BresenhamLineDrawer(pixelDrawer);
    }

    @Override
    public void drawArc(int centerX, int centerY, int width, int height, double startAngle, double arcAngle, Color color) {
        if (arcAngle == 0) return;
        int a = width / 2;
        int b = height / 2;

        int x = -a, y = 0;
        int e2 = b, dx = (1 + 2 * x) * e2 * e2;
        int dy = x * x, err = dx + dy;
        do {
            drawPoints(centerX, centerY, x, y, startAngle, arcAngle, color);
            e2 = 2 * err;
            if (e2 > dx) {
                x++;
                err += dx += 2 * b * b;
            } /* x step */
            if (e2 < dy) {
                y++;
                err += dy += 2 * a * a;
            } /* y step */
        } while (x <= 0);

        while (y++ < b) {
            drawPoints(centerX, centerY, x, y, startAngle, arcAngle, color);
        }
    }

    private double swap(double first, double second) {
        return first;
    }

     void drawPoints(int centerX, int centerY, int x, int y, double startAngle, double arcAngle, Color color) {
        if (arcAngle >= 360) {
            drawSymmetricPixels(centerX, centerY, x, y, color);
            return;
        }
        double angle = Math.toDegrees(Math.atan2(y, x));
        startAngle %= 360;
        arcAngle %= 360;
        double endAngle = (startAngle + arcAngle) % 360 == 0.0 ? 360 : (startAngle + arcAngle) % 360;

        boolean mustReverse = false;

        if (endAngle < startAngle) {
            endAngle = swap(startAngle, startAngle = endAngle);
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


    private int withoutPeriod(int angle) {
        while (angle > 360) {
            angle -= 360;
        }
        return angle;
    }

    private void drawSymmetricPixels(int centerX, int centerY, int x, int y, Color color) {
        pixelDrawer.drawPixel(centerX + x, centerY + y, color);
        pixelDrawer.drawPixel(centerX + x, centerY - y, color);
        pixelDrawer.drawPixel(centerX - x, centerY - y, color);
        pixelDrawer.drawPixel(centerX - x, centerY + y, color);
    }

    void fillSymmetric(int centerX, int centerY, int x, int y, Color color) {
        lineDrawer.drawLine(centerX + x, centerY + y, centerX - x, centerY + y, color);
        lineDrawer.drawLine(centerX + x, centerY - y, centerX - x, centerY - y, color);
    }
}
