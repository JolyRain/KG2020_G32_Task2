package test.graphics_impl;

import test.ArcDrawer;
import test.GraphicsProvider;
import test.LineDrawer;
import test.PieDrawer;
import test.graphics_impl.arc.ArcDrawerFactoryByPixelDrawer;
import test.graphics_impl.arc.GraphicsArcDrawer;
import test.graphics_impl.line.GraphicsLineDrawer;
import test.graphics_impl.line.LineDrawerFactoryByPixelDrawer;
import test.graphics_impl.pie.GraphicsPieDrawer;
import test.graphics_impl.pie.PieDrawerFactoryByPixelDrawer;

public class PrimitivesFactoryWithDefaultGraphicsImplementation {

    public final LineDrawer createLineDrawer(GraphicsProvider gp) {
        LineDrawerFactoryByPixelDrawer factory = getCustomLineDrawerFactory();
        if (factory != null)
            return factory.createInstance(new GraphicsPixelDrawer(gp));
        return new GraphicsLineDrawer(gp);
    }

    public final ArcDrawer createArcDrawer(GraphicsProvider gp) {
        ArcDrawerFactoryByPixelDrawer factory = getCustomArcDrawerFactory();
        if (factory != null)
            return factory.createInstance(new GraphicsPixelDrawer(gp));
        return new GraphicsArcDrawer(gp);
    }

    public final PieDrawer createPieDrawer(GraphicsProvider gp) {
        PieDrawerFactoryByPixelDrawer factory = getCustomPieDrawerFactory();
        if (factory != null)
            return factory.createInstance(new GraphicsPixelDrawer(gp));
        return new GraphicsPieDrawer(gp);
    }

    protected ArcDrawerFactoryByPixelDrawer getCustomArcDrawerFactory() {
        return null;
    }
    protected PieDrawerFactoryByPixelDrawer getCustomPieDrawerFactory() {
        return null;
    }
    protected LineDrawerFactoryByPixelDrawer getCustomLineDrawerFactory() {
        return null;
    }
}
