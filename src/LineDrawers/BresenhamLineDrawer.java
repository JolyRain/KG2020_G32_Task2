package LineDrawers;

import utils.LineDrawer;
import utils.PixelDrawer;

import java.awt.*;

public class BresenhamLineDrawer implements LineDrawer {

    private PixelDrawer pixelDrawer;

    public BresenhamLineDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {

        int x = x1;
        int y = y1;
        int absDx = Math.abs(x2 - x1);
        int absDy = Math.abs(y2 - y1);

        int directionY = y2 - y1;
        int directionX = x2 - x1;

        if (directionY > 0) directionY = 1;
        else directionY = -1;

        if (directionX > 0) directionX = 1;
        else directionX = -1;

        if (absDx >= absDy) {
            int error = 2 * absDy - absDx;
            for (int i = 0; i < absDx; i++) {
                pixelDrawer.drawPixel(x, y, color);
                if (error >= 0) {
                    y += directionY;
                    error += 2 * (absDy - absDx);
                } else error += 2 * absDy;
                x += directionX;
            }
        } else {
            int error = 2 * absDx - absDy;
            for (int i = 0; i < absDy; i++) {
                pixelDrawer.drawPixel(x, y, color);
                if (error >= 0) {
                    x += directionX;
                    error += 2 * (absDx - absDy);
                } else error += 2 * absDx;
                y += directionY;
            }
        }
    }
}
