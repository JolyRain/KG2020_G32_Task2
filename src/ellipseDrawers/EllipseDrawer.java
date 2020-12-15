package ellipseDrawers;

import java.awt.*;

public interface EllipseDrawer {

    void drawEllipse(int x, int y, int width, int height, Color color);
    void fillEllipse(int x, int y, int width, int height, Color color);

}
