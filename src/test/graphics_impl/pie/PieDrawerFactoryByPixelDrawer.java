package test.graphics_impl.pie;

import test.PieDrawer;
import test.PixelDrawer;

public interface PieDrawerFactoryByPixelDrawer {
    PieDrawer createInstance(PixelDrawer pd);
}
