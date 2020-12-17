package tests.graphics_impl.pie;

import tests.PieDrawer;
import tests.PixelDrawer;

public interface PieDrawerFactoryByPixelDrawer {
    PieDrawer createInstance(PixelDrawer pd);
}
