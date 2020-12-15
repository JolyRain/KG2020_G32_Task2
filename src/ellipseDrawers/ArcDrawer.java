package ellipseDrawers;

import java.awt.*;

public interface ArcDrawer {
    void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle, Color color);

    void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle, Color color);
}
