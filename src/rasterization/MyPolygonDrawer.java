package rasterization;

import lineDrawers.LineDrawer;
import pixelDrawers.PixelDrawer;
import utils.ScreenPoint;

import java.awt.*;

public class MyPolygonDrawer implements PolygonDrawer {
    private LineDrawer lineDrawer;
    private PixelDrawer pixelDrawer;


    public MyPolygonDrawer(LineDrawer lineDrawer, PixelDrawer pixelDrawer) {
        this.lineDrawer = lineDrawer;
        this.pixelDrawer = pixelDrawer;
    }

    @Override
    public void drawPolygon(ScreenPoint point1, ScreenPoint point2, ScreenPoint point3, Color color) {
        lineDrawer.drawLine(point1, point2, color);
        lineDrawer.drawLine(point2, point3, color);
        lineDrawer.drawLine(point3, point1, color);
    }

    @Override
    public void fillPolygon(ScreenPoint point1, ScreenPoint point2, ScreenPoint point3, Color color) {
        drawPolygon(point1, point2, point3, color);
        ScreenPoint topLeft = topLeft(point1, point2, point3);
        ScreenPoint bottomRight = bottomRight(point1, point2, point3);
        for (int y = topLeft.getY(); y <= bottomRight.getY(); y++) {
            for (int x = topLeft.getX(); x <= bottomRight.getX(); x++) {
                if (isInnerPoint(new ScreenPoint(x, y), point1, point2, point3)) {
                    pixelDrawer.drawPixel(x, y, color);
                }
            }
        }
    }

    private boolean isInnerPoint(ScreenPoint point, ScreenPoint a, ScreenPoint b, ScreenPoint c) {
        // Используем преобразовнное уравнение прямой проходящих по двум точкам - (y-y1)/(y2-y1) = (x-x1)/(x2-x1)
        // Находим 3 уравнения прямых по двум точкам каждой стороны треугольника, подставляя координаты проверяемой точки
        // Если точка выше - значение будет выше ноля, если ниже - ниже ноля, если равно - на прямой
        // Слудет учитывать что уравнение прямой от точки A к B не совсем то же, что от точкии B к А, это своего рода инверсия,
        // поэтому следует учитывать ориентацию прямых (в каком порядке идут точки), либо делать 2 прверки, для универсальности
        int aSide = getSide(point, a, b);
        int bSide = getSide(point, b, c);
        int cSide = getSide(point, c, a);
        return (aSide >= 0 && bSide >= 0 && cSide >= 0) || (aSide < 0 && bSide < 0 && cSide < 0);
    }

    private int getSide(ScreenPoint point, ScreenPoint p1, ScreenPoint p2) {
        return (p1.getY() - p2.getY()) * point.getX()
                + (p2.getX() - p1.getX()) * point.getY()
                + (p1.getX() * p2.getY() - p2.getX() * p1.getY());
    }

    private ScreenPoint topLeft(ScreenPoint point1, ScreenPoint point2, ScreenPoint point3) {
        int xMin = Math.min(point1.getX(), Math.min(point2.getX(), point3.getX()));
        int yMin = Math.min(point1.getY(), Math.min(point2.getY(), point3.getY()));
        return new ScreenPoint(xMin, yMin);
    }

    private ScreenPoint bottomRight(ScreenPoint point1, ScreenPoint point2, ScreenPoint point3) {
        int xMax = Math.max(point1.getX(), Math.max(point2.getX(), point3.getX()));
        int yMax = Math.max(point1.getY(), Math.max(point2.getY(), point3.getY()));
        return new ScreenPoint(xMax, yMax);
    }

    public LineDrawer getLineDrawer() {
        return lineDrawer;
    }

    public void setLineDrawer(LineDrawer lineDrawer) {
        this.lineDrawer = lineDrawer;
    }

    public PixelDrawer getPixelDrawer() {
        return pixelDrawer;
    }

    public void setPixelDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }
}
