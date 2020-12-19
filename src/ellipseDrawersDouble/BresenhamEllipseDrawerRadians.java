package ellipseDrawersDouble;

import ellipseDrawers.EllipseDrawer;
import ellipseDrawers.PieDrawer;
import pixelDrawers.PixelDrawer;

import java.awt.*;

public class BresenhamEllipseDrawerRadians implements EllipseDrawer {

    private PixelDrawer pixelDrawer;
    private BresenhamArcDrawerRadians bresenhamArcDrawerRadians;

    public BresenhamEllipseDrawerRadians(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
        this.bresenhamArcDrawerRadians = new BresenhamArcDrawerRadians(pixelDrawer);
    }

    @Override
    public void drawEllipse(int centerX, int centerY, int width, int height, Color color) {
        bresenhamArcDrawerRadians.drawArc(centerX, centerY, width, height, 0, 2 * Math.PI, color);
    }

    @Override
    public void fillEllipse(int centerX, int centerY, int width, int height, Color color) {
        PieDrawer pieDrawer = new BresenhamPieDrawerRadians(pixelDrawer);
        pieDrawer.fillPie(centerX, centerY, width, height, 0, 2 * Math.PI, color);

    }

}
