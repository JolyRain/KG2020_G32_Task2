package tests.myImpl;

import tests.ArcDrawer;

import java.awt.*;

public class ArcDrawerImpl implements ArcDrawer {
    private ellipseDrawers.ArcDrawer myArcDrawer;

    public ArcDrawerImpl(ellipseDrawers.ArcDrawer myArcDrawer) {
        this.myArcDrawer = myArcDrawer;
    }

    @Override
    public void drawArc(int x, int y, int width, int height, double startAngle, double arcAngle, Color c) {
        /*Не забываем правильно адаптировать аргументы, т.к. смысловая нагрузка у них немного разная (см. описание)*/
        int centerX = x + width / 2;
        int centerY = y + height / 2;
        double aStart = 180 * startAngle / Math.PI;
        double aArc = 180 * arcAngle / Math.PI;
//        double aEnd = aStart + aArc;

        myArcDrawer.drawArc(centerX, centerY, width, height, startAngle, arcAngle, c);
    }
}
