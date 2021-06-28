package view.components.panels;

import javax.swing.*;
import java.awt.*;
import static view.GameView.screenSize;

public class FullScreenPanel extends JPanel {
    public FullScreenPanel() {
        this(new GridBagLayout());
    }

    public FullScreenPanel(LayoutManager layout) {
        super(layout);
        setBounds(0, 0, screenSize.width, screenSize.height-50);
    }
}
