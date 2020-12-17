package tests.graphics_impl.line;

import tests.LineDrawer;
import tests.PixelDrawer;

public interface LineDrawerFactoryByPixelDrawer {
    LineDrawer createInstance(PixelDrawer pd);
}
