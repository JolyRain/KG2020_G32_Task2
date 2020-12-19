package tests.myImpl;

import tests.PieDrawer;
import tests.PieFiller;

import java.awt.*;

public class PieDrawerImpl implements PieDrawer, PieFiller {
    private ellipseDrawers.PieDrawer myPieDrawer;

    public PieDrawerImpl(ellipseDrawers.PieDrawer myPieDrawer) {
        this.myPieDrawer = myPieDrawer;
    }

    @Override
    public void drawPie(int x, int y, int width, int height, double startAngle, double arcAngle, Color c) {
        int centerX = x + width / 2;
        int centerY = y + height / 2;
        double aStart = 180 * startAngle / Math.PI;
        double aArc = 180 * arcAngle / Math.PI;
        double aEnd = aStart + aArc;

        myPieDrawer.drawPie(centerX, centerY, width, height, startAngle, arcAngle, c);

    }

    @Override
    public void fillPie(int x, int y, int width, int height, double startAngle, double arcAngle, Color c) {
        int centerX = x + width / 2;
        int centerY = y + height / 2;
        double aStart = 180 * startAngle / Math.PI;
        double aArc = 180 * arcAngle / Math.PI;
        double aEnd = aStart + aArc;

        myPieDrawer.fillPie(centerX, centerY, width, height, startAngle, arcAngle, c);
    }
}
