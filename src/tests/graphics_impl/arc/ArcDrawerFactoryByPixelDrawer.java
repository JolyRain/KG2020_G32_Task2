package tests.graphics_impl.arc;

import tests.ArcDrawer;
import tests.PixelDrawer;

public interface ArcDrawerFactoryByPixelDrawer {
    ArcDrawer createInstance(PixelDrawer pd);
}
