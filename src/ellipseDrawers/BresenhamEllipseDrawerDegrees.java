package ellipseDrawers;

import pixelDrawers.PixelDrawer;

import java.awt.*;

public class BresenhamEllipseDrawerDegrees implements EllipseDrawer {
    private PixelDrawer pixelDrawer;

    public BresenhamEllipseDrawerDegrees(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    @Override
    public void drawEllipse(int centerX, int centerY, int width, int height, Color color) {
        ArcDrawer arcDrawer = new BresenhamArcDrawerDegrees(pixelDrawer);
        arcDrawer.drawArc(centerX, centerY, width, height, 0, 360, color);
    }

    @Override
    public void fillEllipse(int centerX, int centerY, int width, int height, Color color) {
        PieDrawer pieDrawer = new BresenhamPieDrawerDegrees(pixelDrawer);
        pieDrawer.fillPie(centerX, centerY, width, height, 0, 360, color);
    }
}
