package ellipseDrawers;

import lineDrawers.BresenhamLineDrawer;
import lineDrawers.LineDrawer;
import pixelDrawers.PixelDrawer;

import java.awt.*;

public class BresenhamArcDrawer2 implements ArcDrawer {
    private PixelDrawer pixelDrawer;
    private LineDrawer lineDrawer;

    public BresenhamArcDrawer2(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
        lineDrawer = new BresenhamLineDrawer(pixelDrawer);
    }

    private void drawSymmetricPixels(int centerX, int centerY, int x, int y, Color color) {
        pixelDrawer.drawPixel(centerX + x, centerY + y, color);
        pixelDrawer.drawPixel(centerX + x, centerY - y, color);
        pixelDrawer.drawPixel(centerX - x, centerY - y, color);
        pixelDrawer.drawPixel(centerX - x, centerY + y, color);
    }

    @Override
    public void drawArc(int centerX, int centerY, int width, int height, int startAngle, int arcAngle, Color color) {
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
        while (y++ < b) { /* to early stop for flat ellipses with a=1, */
            drawPoints(centerX, centerY, x, y, startAngle, arcAngle, color);
        }


        //        int x = -width;
        //        int y = 0;
        //        int e2;
        //
        //        int dx = -height * height * (2 * width - 1);
        //        int dy = width * width;
        //        int e = dx + dy;
        //
        //        while (x <= 0) {
        //            drawPoints(centerX, centerY, x, y, startAngle, arcAngle, color);
        //            e2 = e * 2;
        //            if (e2 < dy) {
        //                y++;
        //                dy += 2 * (width * width);
        //                e += dy;
        //            }
        //            if (e2 > dx) {
        //                x++;
        //                dx += 2 * (height * height);
        //                e += dx;
        //            }
        //        }
    }

    private int withoutPeriod(int angle) {
        while (angle > 360) {
            angle -= 360;
        }
        return angle;
    }

    private void drawPoints(int centerX, int centerY, int x, int y, int startAngle, int arcAngle, Color color) {
        if (arcAngle >= 360) {
            drawSymmetricPixels(centerX, centerY, x, y, color);
            return;
        }
        double angle = Math.toDegrees(Math.atan2(y, x));
        startAngle %= 360;
        arcAngle %= 360;
        int endAngle = (startAngle + arcAngle) % 360;

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

    private boolean reverse(boolean predicate, boolean reverse) {
        return reverse != predicate;
    }

    @Override
    public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle, Color color) {
        fillArcLevel(x, y, width - 2, height, startAngle, arcAngle, color);
        fillArcLevel(x, y, width, height - 2, startAngle, arcAngle, color);
        fillArcLevel(x, y, width - 2, height - 2, startAngle, arcAngle, color);
    }

    private void fillArcLevel(int centerX, int centerY, int width, int height, int startAngle, int arcAngle, Color color) {
        if (arcAngle >= 360) {
            startAngle = 0;
            arcAngle = 360;
        }
        width /= 2;
        height /= 2;

        int x = -width;
        int y = 0;
        int e2;

        int dx = -height * height * (2 * width - 1);
        int dy = width * width;
        int e = dx + dy;

        while (x <= 0) {
            fillEllipsePoints(centerX, centerY, x, y, startAngle, arcAngle, color);
            e2 = e * 2;
            if (e2 < dy) {
                y++;
                dy += 2 * (width * width);
                e += dy;
            }
            if (e2 > dx) {
                x++;
                dx += 2 * (height * height);
                e += dx;
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
