package tests.myImpl;

import ellipseDrawers.ArcDrawer;
import ellipseDrawers.BresenhamArcDrawer;
import ellipseDrawers.BresenhamArcDrawer2;
import pixelDrawers.PixelDrawer;
import tests.graphics_impl.PrimitivesFactoryWithDefaultGraphicsImplementation;
import tests.graphics_impl.arc.ArcDrawerFactoryByPixelDrawer;

public class MyFactoryImplementation extends PrimitivesFactoryWithDefaultGraphicsImplementation {
    @Override
    protected ArcDrawerFactoryByPixelDrawer getCustomArcDrawerFactory() {
        return new ArcDrawerFactoryByPixelDrawer() {
            @Override
            public tests.ArcDrawer createInstance(tests.PixelDrawer pd) {
                PixelDrawer myPixelDrawer = new MyPixelDrawerImpl(pd);
                ArcDrawer myArcDrawer = new BresenhamArcDrawer2(myPixelDrawer);

                /*Здесь будет создаваться и возвращаться экземпляр класса, реализующего PieDrawer*/
                return new ArcDrawerImpl(myArcDrawer);
            }
        };
    }
}
