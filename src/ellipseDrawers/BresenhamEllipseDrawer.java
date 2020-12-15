package ellipseDrawers;

import lineDrawers.BresenhamLineDrawer;
import lineDrawers.LineDrawer;
import pixelDrawers.PixelDrawer;

import java.awt.*;

public class BresenhamEllipseDrawer implements EllipseDrawer {

    private PixelDrawer pixelDrawer;
    private LineDrawer lineDrawer;

    public BresenhamEllipseDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
        lineDrawer = new BresenhamLineDrawer(pixelDrawer);
    }

    @Override
    public void drawEllipse(int x, int y, int width, int height, Color color) {
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

    @Override
    public void fillEllipse(int x, int y, int width, int height, Color color) {
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
            fillSymmetric(centerX, centerY, x, y, color);
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
            fillSymmetric(centerX, centerY, x, y, color);
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

    private void drawSymmetricPixels(int centerX, int centerY, int x, int y, Color color) {
        pixelDrawer.drawPixel(centerX + x, centerY + y, color);
        pixelDrawer.drawPixel(centerX + x, centerY - y, color);
        pixelDrawer.drawPixel(centerX - x, centerY - y, color);
        pixelDrawer.drawPixel(centerX - x, centerY + y, color);
    }

    private void fillSymmetric(int centerX, int centerY, int x, int y, Color color) {
        lineDrawer.drawLine(centerX + x, centerY + y, centerX - x, centerY + y, color);
        lineDrawer.drawLine(centerX + x, centerY - y, centerX - x, centerY - y, color);
    }
}
