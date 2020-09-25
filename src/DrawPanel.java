import LineDrawers.BresenhamLineDrawer;
import LineDrawers.DDALineDrawer;
import LineDrawers.GraphicsLineDrawer;
import utils.DrawUtils;
import utils.LineDrawer;
import utils.PixelDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel implements MouseMotionListener {

    private Point position = new Point(0, 0);

    public DrawPanel() {
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics buffGraphics = bufferedImage.createGraphics();
        PixelDrawer pd = new GraphicsPixelDrawer(buffGraphics);
        LineDrawer lineDrawer = new BresenhamLineDrawer(pd);
        buffGraphics.setColor(Color.WHITE);
        buffGraphics.fillRect(0, 0, getWidth(), getHeight());
        buffGraphics.setColor(Color.BLACK);
        drawAll(lineDrawer);
        g.drawImage(bufferedImage, 0, 0, null);
        buffGraphics.dispose();
    }

    private void drawAll(LineDrawer lineDrawer) {
//        DrawUtils.drawSnowflake(lineDrawer, getWidth() / 2, getHeight() / 2, 100, 64);
        lineDrawer.drawLine(getWidth() / 2, getHeight() / 2, position.x, position.y, Color.RED);

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        position = new Point(e.getX(), e.getY());
        repaint();
    }
}
