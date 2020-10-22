package app;

import lineDrawers.BresenhamLineDrawer;
import lineDrawers.DDALineDrawer;
import lineDrawers.LineDrawer;
import lineDrawers.WuLineDrawer;
import pixelDrawers.BufferedImagePixelDrawer;
import pixelDrawers.GraphicsPixelDrawer;
import pixelDrawers.PixelDrawer;
import utils.Line;
import utils.RealPoint;
import utils.ScreenConverter;
import utils.ScreenPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;


public class DrawPanel extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener, KeyListener {
    private ScreenConverter screenConverter = new ScreenConverter(
            -2, 2, 4, 4, getWidth(), getHeight());

    private ScreenPoint lastPosition = null;
    private List<Line> allLines = new LinkedList<>();
    private Line currentNewLine = null;

    private PixelDrawer pixelDrawer = null;
    private LineDrawer lineDrawer = null;
    private DrawMode drawMode = DrawMode.DDA;


    public DrawPanel() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addMouseWheelListener(this);
    }

    private void drawLegend(Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLACK);
        graphics2D.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        graphics2D.drawString("Selected mode: " + drawMode, 0, getHeight() - 100);
        for (int i = 1; i < 4; i++) {
            graphics2D.drawString("Key " + i + " -- " + DrawMode.values()[i - 1], 0, getHeight() - 100 + i * 20);
        }
    }
    public static void drawSnowFlake(LineDrawer lineDrawer, int x, int y, int r, int n) {
        double da = 2 * Math.PI / n;
        for (int i = 0; i < n; i++) {
            double dx = r * Math.cos(da * i) + x;
            double dy = r * Math.sin(da * i) + y;
            lineDrawer.drawLine(x, y, (int) dx, (int) dy, Color.BLACK);
        }
    }
    @Override
    public void paint(Graphics g) {
        screenConverter.setScreenWidth(getWidth());
        screenConverter.setScreenHeight(getHeight());
        BufferedImage bufferedImage = new BufferedImage(
                screenConverter.getScreenWidth(), screenConverter.getScreenHeight(), BufferedImage.TYPE_INT_RGB);
        pixelDrawer = new BufferedImagePixelDrawer(bufferedImage);
//        pixelDrawer = new GraphicsPixelDrawer(g);
        switch (drawMode) {
            case DDA:
                lineDrawer = new DDALineDrawer(pixelDrawer);
                break;
            case BRESENHAM:
                lineDrawer = new BresenhamLineDrawer(pixelDrawer);
                break;
            case WU:
                lineDrawer = new WuLineDrawer(pixelDrawer);
                break;
        }
        Graphics2D buffGraphics = bufferedImage.createGraphics();
        buffGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//       g.setColor(Color.WHITE);
//        g.fillRect(0, 0, getWidth(), getHeight());
        buffGraphics.setColor(Color.WHITE);
        buffGraphics.fillRect(0, 0, getWidth(), getHeight());
//        drawLegend(buffGraphics);

//         drawSnowFlake(lineDrawer, getWidth() / 2, getHeight() / 2, 300, 10);
        for (Line line : allLines) {
            drawLine(lineDrawer, line);
        }
        if (currentNewLine != null) drawLine(lineDrawer, currentNewLine);

        g.drawImage(bufferedImage, 0, 0, null);
//
        buffGraphics.dispose();
    }

    private void drawLine(LineDrawer lineDrawer, Line line) {
        lineDrawer.drawLine(
                screenConverter.realToScreen(line.getP1()),
                screenConverter.realToScreen(line.getP2()),
                Color.BLACK);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) lastPosition = new ScreenPoint(e.getX(), e.getY());
        else if (e.getButton() == MouseEvent.BUTTON1) {
            currentNewLine = new Line(
                    screenConverter.screenToReal(new ScreenPoint(e.getX(), e.getY())),
                    screenConverter.screenToReal(new ScreenPoint(e.getX(), e.getY())),
                    Color.BLACK);
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) lastPosition = null;
        else if (e.getButton() == MouseEvent.BUTTON1) {
            allLines.add(currentNewLine);
            currentNewLine = null;
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        ScreenPoint currentPosition = new ScreenPoint(e.getX(), e.getY());
        if (lastPosition != null) {
            ScreenPoint deltaScreen = new ScreenPoint(
                    currentPosition.getX() - lastPosition.getX(),
                    currentPosition.getY() - lastPosition.getY());
            RealPoint deltaReal = screenConverter.screenToReal(deltaScreen);
            RealPoint zeroReal = screenConverter.screenToReal(new ScreenPoint(0, 0));
            RealPoint vector = new RealPoint(
                    deltaReal.getX() - zeroReal.getX(),
                    deltaReal.getY() - zeroReal.getY());
            screenConverter.setCornerX(screenConverter.getCornerX() - vector.getX());
            screenConverter.setCornerY(screenConverter.getCornerY() - vector.getY());
            lastPosition = currentPosition;
        }
        if (currentNewLine != null) {
            currentNewLine.setP2(screenConverter.screenToReal(currentPosition));
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int clicks = e.getWheelRotation();
        double scale = 1;
        double coef = clicks < 0 ? 1.05 : 0.95;
        for (int i = 0; i < Math.abs(clicks); i++) {
            scale *= coef;
        }
        screenConverter.setRealWidth(screenConverter.getRealWidth() * scale);
        screenConverter.setRealHeight(screenConverter.getRealHeight() * scale);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_1:
                drawMode = DrawMode.DDA;
                break;
            case KeyEvent.VK_2:
                drawMode = DrawMode.BRESENHAM;
                break;
            case KeyEvent.VK_3:
                drawMode = DrawMode.WU;
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}


