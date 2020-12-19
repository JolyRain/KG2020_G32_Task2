package ellipseDrawersDouble;

import ellipseDrawers.ArcDrawer;
import pixelDrawers.PixelDrawer;

import java.awt.*;

public class BresenhamArcDrawerRadians implements ArcDrawer {
    private PixelDrawer pixelDrawer;

    public BresenhamArcDrawerRadians(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    @Override
    public void drawArc(int centerX, int centerY, int width, int height, double startAngle, double arcAngle, Color color) {
        if (arcAngle == 0) return;
        if (arcAngle >= 2 * Math.PI) {
            startAngle = 0;
            arcAngle = 2 * Math.PI;
        }
        int a = width / 2;
        int b = height / 2;

        int x = -a, y = 0;
        int e2 = b, dx = (1 + 2 * x) * e2 * e2;
        int dy = x * x, err = dx + dy;
        do {
            drawPixels(x, y, centerX, centerY, width, height, startAngle, arcAngle, color);
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

//        while (y++ < b) {
//            drawPixels(x, y, centerX, centerY, width, height, startAngle, arcAngle, color);
//        }
    }

    void drawPixels(int x, int y, int centerX, int centerY, int width, int height, double startAngle, double arcAngle, Color color) {
        drawEllipsePixel(x, y, centerX, centerY, width, height, startAngle, arcAngle, color);
        drawEllipsePixel(-x, y, centerX, centerY, width, height, startAngle, arcAngle, color);
        drawEllipsePixel(x, -y, centerX, centerY, width, height, startAngle, arcAngle, color);
        drawEllipsePixel(-x, -y, centerX, centerY, width, height, startAngle, arcAngle, color);
    }

    void drawEllipsePixel(int x, int y, int centerX, int centerY, int width, int height, double startAngle, double arcAngle, Color color) {
        if (isPointInside(x, y, width, height, startAngle, arcAngle)) {
            pixelDrawer.drawPixel(centerX + x, centerY - y, color);
        }
    }


}
