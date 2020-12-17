package tests.graphics_impl.arc;

import tests.ArcDrawer;
import tests.GraphicsProvider;

import java.awt.*;
import java.awt.geom.Arc2D;

public class GraphicsArcDrawer implements ArcDrawer {
    private GraphicsProvider gp;

    public GraphicsArcDrawer(GraphicsProvider gp) {
        this.gp = gp;
    }

    @Override
    public void drawArc(int x, int y, int width, int height, double startAngle, double arcAngle, Color c) {
        Arc2D arc = new Arc2D.Double(x, y, width, height, startAngle*180/Math.PI, arcAngle*180/Math.PI, Arc2D.OPEN);
        gp.getGraphics().setColor(c);
        gp.getGraphics().draw(arc);
    }
}
