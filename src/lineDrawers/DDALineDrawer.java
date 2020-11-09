package lineDrawers;

import pixelDrawers.PixelDrawer;
import utils.ScreenPoint;

import java.awt.*;

import static java.lang.Math.abs;

public class DDALineDrawer implements LineDrawer {

    private PixelDrawer pixelDrawer;

    public DDALineDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {
        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(abs(dx), abs(dy));

        float stepX = dx / (float) steps;
        float stepY = dy / (float) steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            pixelDrawer.drawPixel((int) x, (int) y, color);
            x += stepX;
            y += stepY;
        }
        pixelDrawer.drawPixel((int) x, (int) y, color);
    }

    @Override
    public void drawLine(ScreenPoint point1, ScreenPoint point2, Color color) {
        drawLine(point1.getX(), point1.getY(), point2.getX(), point2.getY(), color);
    }
}

