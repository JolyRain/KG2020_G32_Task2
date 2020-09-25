package utils;

import java.awt.*;

public class DrawUtils {
    public static void drawSnowflake(LineDrawer lineDrawer, int x, int y, int r, int n) {
        double da = 2 * Math.PI / n;
        for (int i = 0; i < n; i++) {
            double dx1 = r * Math.cos(da * i);
            double dy1 = r * Math.sin(da * i);
            lineDrawer.drawLine(x, y, x + (int) dx1, y + (int) dy1, new Color(100, 0, 100));
        }
    }
}
