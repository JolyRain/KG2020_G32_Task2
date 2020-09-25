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
        int dX = Math.abs(x2 - x1);
        int dY = Math.abs(y2 - y1);

        int directionY = y2 - y1;
        int directionX = x2 - x1;

        if (directionY > 0) directionY = 1;
        else directionY = -1;

        if (directionX > 0) directionX = 1;
        else directionX = -1;

        if (dX >= dY) {
            int error = 2 * dY - dX;
            for (int i = 0; i < dX; i++) {
                pixelDrawer.drawPixel(x, y, color);
                if (error >= 0) {
                    y += directionY;
                    error += 2 * (dY - dX);
                } else {
                    error += 2 * dY;
                }
                x += directionX;
            }
        } else {
            int error = 2 * dX - dY;
            for (int i = 0; i < dY; i++) {
                pixelDrawer.drawPixel(x, y, color);
                if (error >= 0) {
                    x += directionX;
                    error += 2 * (dX - dY);
                } else {
                    error += 2 * dX;
                }
                y += directionY;
            }
        }
    }
}
