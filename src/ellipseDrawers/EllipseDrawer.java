package ellipseDrawers;

import java.awt.*;

public interface EllipseDrawer {

    void drawEllipse(int centerX, int centerY, int width, int height, Color color);

    void fillEllipse(int centerX, int centerY, int width, int height, Color color);
}
