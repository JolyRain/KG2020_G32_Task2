package app;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private DrawPanel drawPanel;

    public MainWindow() throws Exception {
        drawPanel = new DrawPanel();
        this.add(drawPanel);
        this.addKeyListener(drawPanel);
    }
}
