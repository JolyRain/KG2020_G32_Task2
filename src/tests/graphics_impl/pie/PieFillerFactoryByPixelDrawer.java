package tests.graphics_impl.pie;

import tests.PieFiller;
import tests.PixelDrawer;

public interface PieFillerFactoryByPixelDrawer {
    PieFiller createInstance(PixelDrawer pd);
}
