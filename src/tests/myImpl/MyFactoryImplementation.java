package tests.myImpl;

import ellipseDrawers.ArcDrawer;
import ellipseDrawers.BresenhamArcDrawerDegrees;
import ellipseDrawers.BresenhamPieDrawerDegrees;
import ellipseDrawersDouble.BresenhamArcDrawerRadians;
import ellipseDrawersDouble.BresenhamPieDrawerRadians;
import pixelDrawers.PixelDrawer;
import tests.graphics_impl.PrimitivesFactoryWithDefaultGraphicsImplementation;
import tests.graphics_impl.arc.ArcDrawerFactoryByPixelDrawer;
import tests.graphics_impl.pie.PieDrawerFactoryByPixelDrawer;
import tests.graphics_impl.pie.PieFillerFactoryByPixelDrawer;

public class MyFactoryImplementation extends PrimitivesFactoryWithDefaultGraphicsImplementation {
    @Override
    protected ArcDrawerFactoryByPixelDrawer getCustomArcDrawerFactory() {
        return pd -> {
            PixelDrawer myPixelDrawer = new MyPixelDrawerImpl(pd);
            ArcDrawer myArcDrawer = new BresenhamArcDrawerRadians(myPixelDrawer);

            /*Здесь будет создаваться и возвращаться экземпляр класса, реализующего PieDrawer*/
            return new ArcDrawerImpl(myArcDrawer);
        };
    }

    @Override
    protected PieDrawerFactoryByPixelDrawer getCustomPieDrawerFactory() {
        return pd -> {
            PixelDrawer myPixelDrawer = new MyPixelDrawerImpl(pd);
            ellipseDrawers.PieDrawer myPieDrawer = new BresenhamPieDrawerRadians(myPixelDrawer);
            return new PieDrawerImpl(myPieDrawer);
        };
    }

    @Override
    protected PieFillerFactoryByPixelDrawer getCustomPieFillerFactory() {
        return pd ->{
            PixelDrawer myPD = new MyPixelDrawerImpl(pd);
            ellipseDrawers.PieDrawer myPieDrawer = new BresenhamPieDrawerRadians(myPD);
            return new PieDrawerImpl(myPieDrawer);
        };
    }
}
