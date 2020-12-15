package ellipseDrawers;

import java.awt.*;

public interface PieDrawer {

    void drawPie(int x, int y, int a, int b, int startAngle, int pieAngle, Color color);

    void fillPie(int x, int y, int a, int b, int startAngle, int pieAngle, Color color);


}
