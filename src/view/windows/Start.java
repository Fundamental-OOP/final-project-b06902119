package view.windows;

import view.GameView;

import view.components.Background;
import view.components.Logo;

import view.utils.ColorTool;

import javax.swing.*;
import java.awt.*;

public class Start extends JPanel{

    private final JButton start = new JButton("Start");

    public Start() {
        setBackground(new Color(0x97acc2));
        start.setFont(GameView.font);

        initLayeredPane();
    }

    private void initLayeredPane() {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(GameView.screenSize);

        Background background = new Background("src/images/background.png");
        layeredPane.add(background, 0);

        Logo logo = new Logo("src/images/logo.png");
        logo.setLocation(GameView.screenSize.width / 5 * 2, GameView.screenSize.height / 15 * 2);
        layeredPane.add(logo, 0);

        //config next button
        start.setBounds(GameView.screenSize.width / 3, GameView.screenSize.height / 3 * 2, GameView.screenSize.width / 3, GameView.screenSize.height / 15);
        start.setBackground(ColorTool.adjustAlpha(Color.LIGHT_GRAY, 75));
        start.setOpaque(true);
        start.addActionListener(e -> setVisible(false));

        layeredPane.add(start, 0);

        this.add(layeredPane);
    }
}
