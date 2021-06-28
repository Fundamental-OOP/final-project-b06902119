package view.utils;

import javax.swing.*;
import java.awt.*;

public class DiamondIcon implements Icon {

    private final Color color;
    private final int width = 10;
    private final int height = 10;
    private Polygon poly;

    public DiamondIcon(Color color) {
        this.color = color;
        initPolygon();
    }

    private void initPolygon() {
        poly = new Polygon();
        int halfWidth = width / 2;
        int halfHeight = height / 2;
        poly.addPoint(0, halfHeight);
        poly.addPoint(halfWidth, 0);
        poly.addPoint(width, halfHeight);
        poly.addPoint(halfWidth, height);
    }

    public int getIconHeight() {
        return height;
    }

    public int getIconWidth() {
        return width;
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(color);
        g.translate(x, y);
        g.fillPolygon(poly);
        g.translate(-x, -y);
    }
}
