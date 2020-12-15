package test.graphics_impl.line;

import test.LineDrawer;
import test.PixelDrawer;

public interface LineDrawerFactoryByPixelDrawer {
    LineDrawer createInstance(PixelDrawer pd);
}
