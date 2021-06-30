package view.windows;

import view.GameView;
import view.components.Background;
import view.components.Logo;
import view.utils.ColorTool;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class GameOver extends JPanel {

    private final Logo gameOver = new Logo("src/images/GameOver.png");
    private final JButton close = new JButton("Close");
    private Timer timer = new Timer();

    public GameOver() {
        setBackground(new Color(0x97acc2));
        close.setFont(GameView.font);

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

        gameOver.setLocation(GameView.screenSize.width / 5 * 2, GameView.screenSize.height / 15 * 6);
        layeredPane.add(gameOver, 0);

        //config close button
        close.setBounds(GameView.screenSize.width / 3, GameView.screenSize.height / 3 * 2, GameView.screenSize.width / 3, GameView.screenSize.height / 15);
        close.setBackground(ColorTool.adjustAlpha(Color.LIGHT_GRAY, 75));
        close.setOpaque(true);
        close.addActionListener(e -> System.exit(0));

        layeredPane.add(close, 0);

        this.add(layeredPane);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gameOver.setVisible(!gameOver.isVisible());
            }
        }, 0, 500);
    }
}


