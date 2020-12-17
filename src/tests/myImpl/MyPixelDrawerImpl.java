package tests.myImpl;

import pixelDrawers.PixelDrawer;

import java.awt.*;

public class MyPixelDrawerImpl implements PixelDrawer {
    private tests.PixelDrawer pdInstance;

    public MyPixelDrawerImpl(tests.PixelDrawer pd) {
        this.pdInstance = pd;   //?????
    }

    @Override
    public void drawPixel(int x, int y, Color color) {
        pdInstance.setPixel(x, y, color);
    }
}
