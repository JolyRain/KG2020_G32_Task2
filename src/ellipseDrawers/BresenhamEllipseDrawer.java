package ellipseDrawers;

import pixelDrawers.PixelDrawer;

import java.awt.*;

public class BresenhamEllipseDrawer extends BresenhamEllipse implements EllipseDrawer {


    public BresenhamEllipseDrawer(PixelDrawer pixelDrawer) {
        super(pixelDrawer);
    }


    @Override
    public void drawEllipse(int x, int y, int a, int b, Color color) {
        super.draw(x, y, a, b, color);
    }

    @Override
    void drawSymmetricPixels(int centerX, int centerY, int x, int y, Color color) {
        pixelDrawer.drawPixel(centerX + x, centerY + y, color);
        pixelDrawer.drawPixel(centerX + x, centerY - y, color);
        pixelDrawer.drawPixel(centerX - x, centerY - y, color);
        pixelDrawer.drawPixel(centerX - x, centerY + y, color);
    }

}
