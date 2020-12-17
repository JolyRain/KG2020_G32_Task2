package tests.graphics_impl;

import tests.GraphicsProvider;
import tests.PixelDrawer;

import java.awt.*;

public class GraphicsPixelDrawer implements PixelDrawer {
    private GraphicsProvider gp;

    public GraphicsPixelDrawer(GraphicsProvider gp) {
        this.gp = gp;
    }

    @Override
    public void setPixel(int x, int y, Color c) {
        gp.getGraphics().setColor(c);
        gp.getGraphics().fillRect(x, y, 1, 1);
    }
}
