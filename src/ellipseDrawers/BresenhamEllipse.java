package ellipseDrawers;

import pixelDrawers.PixelDrawer;

import java.awt.*;

public abstract class BresenhamEllipse {

    PixelDrawer pixelDrawer;

    public BresenhamEllipse(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    public void draw(int x, int y, int a, int b, Color color) {
        int centerX = x;
        int centerY = y;
        x = 0;
        y = b;
        int squareA = a * a;
        int squareB = b * b;
        int delta = 4 * squareB * ((x + 1) * (x + 1)) + squareA * ((2 * y - 1) * (2 * y - 1)) - 4 * squareA * squareB;

        while (squareA * (2 * y - 1) > 2 * squareB * (x + 1)) {
            drawSymmetricPixels(centerX, centerY, x, y, color);
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
            drawSymmetricPixels(centerX, centerY, x, y, color);
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

     abstract void drawSymmetricPixels(int centerX, int centerY, int x, int y, Color color);
}
