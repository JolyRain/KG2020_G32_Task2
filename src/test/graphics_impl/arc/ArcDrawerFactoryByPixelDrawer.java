package test.graphics_impl.arc;

import test.ArcDrawer;
import test.PixelDrawer;

public interface ArcDrawerFactoryByPixelDrawer {
    ArcDrawer createInstance(PixelDrawer pd);
}
