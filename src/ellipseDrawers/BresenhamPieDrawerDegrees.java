package ellipseDrawers;

import lineDrawers.BresenhamLineDrawer;
import lineDrawers.LineDrawer;
import pixelDrawers.PixelDrawer;
import utils.ScreenPoint;

import java.awt.*;

public class BresenhamPieDrawerDegrees implements PieDrawer {
    private static final double EPS = 0.001;
    private PixelDrawer pixelDrawer;
    private LineDrawer lineDrawer;

    public BresenhamPieDrawerDegrees(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
        lineDrawer = new BresenhamLineDrawer(pixelDrawer);
    }

    @Override
    public void drawPie(int centerX, int centerY, int width, int height, double startAngle, double pieAngle, Color color) {
        ArcDrawer arcDrawer = new BresenhamArcDrawerDegrees(pixelDrawer);
        int a = (width) / 2;
        int b = (height) / 2;
        int startX = (int) (a * Math.cos(startAngle));
        int startY = (int) (b * Math.sin(startAngle));
        int endX = (int) (a * Math.cos(pieAngle));
        int endY = (int) (b * Math.sin(pieAngle));

        ScreenPoint start = getStartPoint(centerX - a, centerY - b, width, height, startAngle, pieAngle);
        ScreenPoint end = getEndPoint(centerX - a, centerY - b, width, height, startAngle, pieAngle);
        lineDrawer.drawLine(centerX, centerY, start.getX(), start.getY(), color);
        lineDrawer.drawLine(centerX, centerY, end.getX(), end.getY(), color);

        arcDrawer.drawArc(centerX, centerY, width, height, startAngle, pieAngle, color);
    }

    public ScreenPoint getStartPoint(int leftX, int leftY, int width, int height, double startAngle, double pieAngle) {
        double angle = Math.toRadians(-startAngle);
        double x = leftX + (Math.cos(angle) * 0.5 + 0.5) * width;
        double y = leftY + (Math.sin(angle) * 0.5 + 0.5) * height;
        return new ScreenPoint((int) x, (int) y);
    }

    public ScreenPoint getEndPoint(int leftX, int leftY, int width, int height, double startAngle, double pieAngle) {
        double angle = Math.toRadians(-startAngle - pieAngle);
        double x = leftX + (Math.cos(angle) * 0.5 + 0.5) * width;
        double y = leftY + (Math.sin(angle) * 0.5 + 0.5) * height;
        return new ScreenPoint((int) x, (int) y);
    }

    @Override
    public void fillPie(int centerX, int centerY, int width, int height, double startAngle, double pieAngle, Color color) {

        ArcDrawer arcDrawer = new BresenhamArcDrawerDegrees(pixelDrawer) {
            @Override
            void drawPoints(int centerX, int centerY, int x, int y, double startAngle, double arcAngle, Color color) {
                if (arcAngle >= 360) {
                    fillSymmetric(centerX, centerY, x, y, color);
                    return;
                }
                for (int i = -y; i <= y; i++) {
                    super.drawPoints(centerX, centerY, x, i, startAngle, arcAngle, color);
                }
            }

        };
        arcDrawer.drawArc(centerX, centerY, width, height, startAngle, pieAngle, color);

        int a = (width) / 2;
        int b = (height) / 2;
        ScreenPoint start = getStartPoint(centerX - a, centerY - b, width, height, startAngle, pieAngle);
        ScreenPoint end = getEndPoint(centerX - a, centerY - b, width, height, startAngle, pieAngle);

        if (!drawLine(centerX, centerY, width, height, startAngle, color))
            lineDrawer.drawLine(centerX, centerY, start.getX(), start.getY(), color);
//        if (!drawLine(centerX, centerY, width, height, pieAngle, color))
            lineDrawer.drawLine(centerX, centerY, end.getX(), end.getY(), color);
    }


    private boolean drawLine(int centerX, int centerY, int width, int height, double angle, Color color) {
        int a = width / 2;
        int b = height / 2;
        if (angle == 0) {
            return true;
        }
        if (angle == 90) {
            return true;
        }
        if (angle == 180) {
            lineDrawer.drawLine(centerX, centerY, centerX - a, centerY, color);
            return true;
        }
        if (angle == 270) {
            lineDrawer.drawLine(centerX, centerY, centerX, centerY + b, color);
            return true;
        }
        return false;
    }

//    @Override
//    void drawPoints(int centerX, int centerY, int x, int y, double startAngle, double arcAngle, Color color) {
//        if (arcAngle >= 360) {
//            fillSymmetric(centerX, centerY, x, y, color);
//            return;
//        }
//        double angle = Math.toDegrees(Math.atan2(y, x));
//        startAngle = startAngle % 360;
//        arcAngle = arcAngle % 360;
//        double endAngle = (startAngle + arcAngle) % 360;
//
//        boolean mustReverse = false;
//
//        if (endAngle < startAngle) {
//            double buf = endAngle;
//            endAngle = startAngle;
//            startAngle = buf;
//            mustReverse = true;
//        }
//
//        // 0 - 90
//        if (reverse(180 - angle >= startAngle && 180 - angle <= endAngle, mustReverse)) {
//            lineDrawer.drawLine(centerX, centerY, centerX - x, centerY - y, color);
//        }
//
//        // 90 - 180
//        if (reverse(angle >= startAngle && angle <= endAngle, mustReverse)) {
//            lineDrawer.drawLine(centerX, centerY, centerX + x, centerY - y, color);
//        }
//
//        // 180 - 270
//        if (reverse(360 - angle >= startAngle && 360 - angle <= endAngle, mustReverse)) {
//            lineDrawer.drawLine(centerX, centerY, centerX + x, centerY + y, color);
//        }
//
//        // 270 - 360
//        if (reverse(angle + 180 >= startAngle && angle + 180 <= endAngle, mustReverse)) {
//            lineDrawer.drawLine(centerX, centerY, centerX - x, centerY + y, color);
//        }
////                System.out.println("x " + x + "  y " + y);
////                System.out.println(startAngle + " + " + arcAngle + " = " + startAngle + arcAngle);
//    }
//};
//        arcDrawer.drawArc(centerX, centerY, width - 2, height - 2, startAngle, pieAngle, color);
//                arcDrawer.drawArc(centerX, centerY, width, height - 2, startAngle, pieAngle, color);
//                arcDrawer.drawArc(centerX, centerY, width - 2, height, startAngle, pieAngle, color);
//                arcDrawer.drawArc(centerX, centerY, width, height, startAngle, pieAngle, color);
}
