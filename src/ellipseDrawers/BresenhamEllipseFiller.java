package ellipseDrawers;

import lineDrawers.LineDrawer;
import pixelDrawers.PixelDrawer;

import java.awt.*;

public class BresenhamEllipseFiller extends BresenhamEllipse implements EllipseFiller {

    private LineDrawer lineDrawer;

    public BresenhamEllipseFiller(PixelDrawer pixelDrawer, LineDrawer lineDrawer) {
        super(pixelDrawer);
        this.lineDrawer = lineDrawer;
    }

    @Override
    public void fillEllipse(int x, int y, int a, int b, Color color) {
        super.draw(x, y, a, b, color);
    }

    @Override
    void drawSymmetricPixels(int centerX, int centerY, int x, int y, Color color) {
        lineDrawer.drawLine(centerX + x, centerY + y, centerX - x, centerY + y, color);
        lineDrawer.drawLine(centerX + x, centerY - y, centerX - x, centerY - y, color);
    }
}
