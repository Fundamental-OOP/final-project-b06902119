package view.components;

import view.components.panels.FullScreenPanel;

import javax.swing.*;
import java.awt.*;

public class Background extends FullScreenPanel {

    private static String backgroundImgPath;

    public Background(String path) {
        super();
        backgroundImgPath = path;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Image img = new ImageIcon(backgroundImgPath).getImage();
        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}