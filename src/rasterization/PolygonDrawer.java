package rasterization;

import utils.ScreenPoint;

import java.awt.*;

public interface PolygonDrawer {

    void drawPolygon(ScreenPoint point1, ScreenPoint point2, ScreenPoint point3, Color color);

    void fillPolygon(ScreenPoint point1, ScreenPoint point2, ScreenPoint point3, Color color);

    default int swap(int first, int second) {
        return first;
    }

}
