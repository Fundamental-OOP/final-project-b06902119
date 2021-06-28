package view.components;

import view.GameView;
import javax.swing.*;

public class Logo extends JPanel {
    private final LogoLabel logo;
    public Logo(String path) {
        logo = new LogoLabel(path);
        add(logo);
        setOpaque(false);
    }

    public void setLocation(int x, int y) {
        setBounds(x, y, logo.width, logo.height);
    }

    private static class LogoLabel extends JLabel {
        private final int width = GameView.screenSize.width/5, height = GameView.screenSize.height/5;
        LogoLabel(String path) {
            setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(width, height, java.awt.Image.SCALE_DEFAULT)));
        }
    }
}
