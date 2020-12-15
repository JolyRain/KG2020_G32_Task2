package test.graphics_impl.pie;

import test.GraphicsProvider;
import test.PieDrawer;

import java.awt.*;
import java.awt.geom.Arc2D;

public class GraphicsPieDrawer implements PieDrawer {
    private GraphicsProvider gp;

    public GraphicsPieDrawer(GraphicsProvider gp) {
        this.gp = gp;
    }

    @Override
    public void drawPie(int x, int y, int width, int height, double startAngle, double arcAngle, Color c) {
        Arc2D arc = new Arc2D.Double(x, y, width, height, startAngle, arcAngle, Arc2D.PIE);
        gp.getGraphics().setColor(c);
        gp.getGraphics().draw(arc);
    }
}
