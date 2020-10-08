package LineDrawers;

import utils.LineDrawer;
import utils.PixelDrawer;

import java.awt.*;

public class WuLineDrawer implements LineDrawer {

    public static final int STEP = 10;
    private PixelDrawer pixelDrawer;

    public WuLineDrawer(PixelDrawer pixelDrawer) {
        this.pixelDrawer = pixelDrawer;
    }

    private int countStep(int i) {
        return (i > 0) ? STEP : -STEP;
    }

    private float fractionalPart(float x) {
        int temp = (int) x;
        return x - temp;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, Color color) {

        int x = x1;
        int y = y1;
        int dx = x2 - x1;
        int dy = y2 - y1;
        int absDx = Math.abs(dx);
        int absDy = Math.abs(dy);
        float gradient;
        int error = 2 * dy - dx;

        dx = countStep(dx);
        dy = countStep(dy);

        Color b1, b2;
        if (absDx >= absDy) {

            gradient = (float) absDy / (float) absDx;
            float entry = y1 + gradient;
            for (int i = 1; i <= absDx; i++) {
                float alpha = fractionalPart(entry);
                float inverselyAlpha = 1f - fractionalPart(entry);

                b1 = new Color(0, 0, 0, inverselyAlpha);
                b2 = new Color(0, 0, 0, alpha);

                if (error >= 0) {
                    pixelDrawer.drawPixel(x, y, b1);
                    pixelDrawer.drawPixel(x, y + dy, b2);
                    y += dy;
                    error += 2 * (absDy - absDx);
                } else {
                    pixelDrawer.drawPixel(x, y, b2);
                    pixelDrawer.drawPixel(x, y - dy, b1);
                    error += 2 * absDy;
                }
                entry += gradient;
                x += dx;
            }
        } else {
            gradient = (float) absDx / absDy;
            float entry = x1 + gradient;
            for (int i = 1; i <= absDy; i++) {
                b1 = new Color(255, 0, 0, (int) (fractionalPart(entry) * 255));
                b2 = new Color(0, 255, 0, (int) (255 - fractionalPart(entry) * 255));

                if (error >= 0) {
                    pixelDrawer.drawPixel(x + 10, y, b2); //green
                    pixelDrawer.drawPixel(x, y, b1);
                    x += dx;
                    error += 2 * (absDx - absDy);
                } else {
                    pixelDrawer.drawPixel(x, y, b1);
                    pixelDrawer.drawPixel(x - 10, y, b2); //green
                    error += 2 * absDx;
                }
                entry += gradient;
                y += dy;
            }
        }
    }
}


