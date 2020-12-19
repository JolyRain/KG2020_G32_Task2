package ellipseDrawersDouble;

import ellipseDrawers.ArcDrawer;
import ellipseDrawers.PieDrawer;
import lineDrawers.BresenhamLineDrawer;
import lineDrawers.LineDrawer;
import pixelDrawers.PixelDrawer;

import java.awt.*;

import static java.lang.Math.PI;

public class BresenhamPieDrawerRadians implements PieDrawer {
    private static final double EPS = 0.001;
    private PixelDrawer pixelDrawer;
    private LineDrawer lineDrawer;


    public BresenhamPieDrawerRadians(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
        lineDrawer = new BresenhamLineDrawer(pixelDrawer);
    }

    @Override
    public void drawPie(int centerX, int centerY, int width, int height, double startAngle, double pieAngle, Color color) {
        ArcDrawer arcDrawer = new BresenhamArcDrawerRadians(pixelDrawer);
        startAngle = arcDrawer.withoutPeriod(startAngle);
        pieAngle = arcDrawer.withoutPeriod(pieAngle);
        double endAngle = arcDrawer.withoutPeriod(startAngle + pieAngle);

        int a = width / 2;
        int b = height / 2;
        int startX = (int) (a * Math.cos(startAngle));
        int startY = (int) (b * Math.sin(startAngle));
        int endX = (int) (a * Math.cos(endAngle));
        int endY = (int) (b * Math.sin(endAngle));
        arcDrawer.drawArc(centerX, centerY, width, height, startAngle, pieAngle, color);
        lineDrawer.drawLine(centerX, centerY, centerX + startX, centerY - startY, color);
        lineDrawer.drawLine(centerX, centerY, centerX + endX, centerY - endY, color);

    }

    @Override
    public void fillPie(int centerX, int centerY, int width, int height, double startAngle, double pieAngle, Color color) {

        BresenhamArcDrawerRadians bresenhamArcDrawerRadians = new BresenhamArcDrawerRadians(pixelDrawer) {
            @Override
            void drawPixels(int x, int y, int centerX, int centerY, int width, int height, double startAngle, double arcAngle, Color color) {
                double start = withoutPeriod(startAngle);
                double end = withoutPeriod(arcAngle + startAngle);

                for (int i = -y; i <= y; i++) {
                    double angle = withoutPeriod(Math.atan2(y / (double) (height / 2), x / (double) (width / 2)));
                    if (arcAngle >= 2 * Math.PI) {
                        pixelDrawer.drawPixel(centerX + x, centerY + i, color);
                        pixelDrawer.drawPixel(centerX + x, centerY - i, color);
                        pixelDrawer.drawPixel(centerX - x, centerY + i, color);
                        pixelDrawer.drawPixel(centerX - x, centerY - i, color);
                    }
                    if (x == 0 && i > 0) {
                        if (start == PI / 2) {
                            drawEllipsePixel(x, -i, centerX, centerY, width, height, startAngle, arcAngle, color);
                            continue;
                        }
                        if (start == 0 && end == PI / 2) {
                            drawEllipsePixel(x, i, centerX, centerY, width, height, startAngle, arcAngle, color);
                            continue;
                        }
                    }
                    if (i == 0) {
                        if (start == PI / 2 && end == 3 * PI / 2) {
                            drawEllipsePixel(x, i, centerX, centerY, width, height, startAngle, arcAngle, color);
                            drawEllipsePixel(-x, i, centerX, centerY, width, height, startAngle, arcAngle, color);
                            continue;

                        }
                        if (start == 3 * PI / 2 && end == PI / 2) {
                            drawEllipsePixel(x, i, centerX, centerY, width, height, startAngle, arcAngle, color);
                            drawEllipsePixel(-x, i, centerX, centerY, width, height, startAngle, arcAngle, color);
                            continue;

                        }
                        if (start == 0 && end == 3 * PI / 2) {
                            drawEllipsePixel(x, i, centerX, centerY, width, height, startAngle, arcAngle, color);
                            drawEllipsePixel(-x, i, centerX, centerY, width, height, startAngle, arcAngle, color);
                            continue;

                        }
                        if ((start == PI / 2) && end == 2 * PI) {
                            drawEllipsePixel(x, i, centerX, centerY, width, height, startAngle, arcAngle, color);
                            drawEllipsePixel(-x, i, centerX, centerY, width, height, startAngle, arcAngle, color);
                            continue;
                        }
                        if ((start == PI) && end == PI / 2) {
                            drawEllipsePixel(x, i, centerX, centerY, width, height, startAngle, arcAngle, color);
                            drawEllipsePixel(-x, i, centerX, centerY, width, height, startAngle, arcAngle, color);
                            continue;

                        }
                        if ((start == 3 * PI / 2) && end == PI) {
//                            drawEllipsePixel(x, i, centerX, centerY, width, height, startAngle, arcAngle, color);
                            drawEllipsePixel(-x, i, centerX, centerY, width, height, startAngle, arcAngle, color);
                            continue;
                        }
                        if (end == 2 * PI) {
                            drawEllipsePixel(x, i , centerX, centerY, width, height, startAngle, arcAngle, color);
                            drawEllipsePixel(-x, i, centerX, centerY, width, height, startAngle, arcAngle, color);
                            continue;
                        }
                        drawEllipsePixel(x, i, centerX, centerY, width, height, startAngle, arcAngle, color);
                        drawEllipsePixel(-x, i, centerX, centerY, width, height, startAngle, arcAngle, color);
                        continue;
                    }

                    drawEllipsePixel(x, i, centerX, centerY, width, height, startAngle, arcAngle, color);
                    drawEllipsePixel(-x, i, centerX, centerY, width, height, startAngle, arcAngle, color);

                }
                startAngle = this.withoutPeriod(startAngle);
                arcAngle = this.withoutPeriod(arcAngle);
                double endAngle = this.withoutPeriod(startAngle + arcAngle);
                int a = width / 2;
                int b = height / 2;

                if (startAngle == 3 * Math.PI / 2 || startAngle == 2 * Math.PI) {
                    int startX = (int) (a * Math.cos(startAngle));
                    int startY = (int) (b * Math.sin(startAngle));
                    lineDrawer.drawLine(centerX, centerY, centerX + startX, centerY - startY, color);
                }
                if (endAngle == PI * 2) {
                    int endX = (int) (a * Math.cos(endAngle));
                    int endY = (int) (b * Math.sin(endAngle));
                    lineDrawer.drawLine(centerX, centerY, centerX + endX, centerY - endY, color);
                }
                if (endAngle == PI / 2) {
                    int endX = (int) (a * Math.cos(endAngle));
                    int endY = (int) (b * Math.sin(endAngle));
                    lineDrawer.drawLine(centerX, centerY - 1, centerX + endX, centerY - endY, color);
                }
                if (endAngle == 3 * PI / 2) {
                    int endX = (int) (a * Math.cos(endAngle));
                    int endY = (int) (b * Math.sin(endAngle));
//                    lineDrawer.drawLine(centerX, centerY, centerX + endX, centerY - endY, color);
                }
                if (startAngle == PI) {
                    int startX = (int) (a * Math.cos(startAngle));
                    int startY = (int) (b * Math.sin(startAngle));
                    lineDrawer.drawLine(centerX - 1, centerY, centerX + startX, centerY - startY, color);
                }

            }

            @Override
            public boolean isPointInside(int x, int y, int width, int height, double startAngle, double arcAngle) {
                startAngle = withoutPeriod(startAngle);
                arcAngle = withoutPeriod(arcAngle);


                double endAngle = withoutPeriod(startAngle + arcAngle);
                double minAngle = Math.min(startAngle, endAngle);
                double maxAngle = Math.max(startAngle, endAngle);

                int a = width / 2;
                int b = height / 2;

                boolean mustReverse = false;

                if (endAngle < startAngle) {
                    mustReverse = true;
                }

                double angle = withoutPeriod(Math.atan2(y / (double) b, x / (double) a) + PI * 2);
                return reverse(minAngle < angle && angle < maxAngle, mustReverse);
            }
        };
        bresenhamArcDrawerRadians.drawArc(centerX, centerY, width, height, startAngle, pieAngle, color);

    }

}
