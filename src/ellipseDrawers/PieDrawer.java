package ellipseDrawers;

import java.awt.*;

public interface PieDrawer {

    void drawPie(int centerX, int centerY, int width, int height, double startAngle, double pieAngle, Color color);

    void fillPie(int centerX, int centerY, int width, int height, double startAngle, double pieAngle, Color color);

}
