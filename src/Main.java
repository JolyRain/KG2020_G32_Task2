import app.MainWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        MainWindow mainWindow = new MainWindow();
        mainWindow.setSize(1280, 720);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
//        mainWindow.setVisible(true);
        mainWindow.setFocusable(true);

    }

}
