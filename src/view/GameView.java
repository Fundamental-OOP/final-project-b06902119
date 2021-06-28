package view;

import controller.GameController;

import view.components.Avatar;

import view.windows.GameOver;
import view.windows.Map;
import view.windows.Menu;
import view.windows.Start;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.util.Arrays;
import java.util.List;

public class GameView extends JFrame {
    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static final Font font = new Font("Serif", Font.BOLD, 20);
    public static String lang = "zh";
    public static List<Avatar> avatars = Arrays.asList(
            new Avatar("src/images/avatars/blue.png"),
            new Avatar("src/images/avatars/yellow.png"),
            new Avatar("src/images/avatars/green.png"),
            new Avatar("src/images/avatars/red.png")
    );

    private GameController controller;
    private final Start start;
    private final Menu menu;
    private final Map map;
    private final GameOver gameOver;

    public GameView() {
        start = new Start();
        menu = new Menu();
        map = new Map();
        gameOver = new GameOver();
    }

    public void launch() {
        setTitle("NTU Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(screenSize.width, screenSize.height);
        setResizable(false);

        CardLayout layout = new CardLayout();
        final JPanel panel = new JPanel(layout);
        panel.add(start, "start");
        panel.add(menu, "menu");
        panel.add(map, "map");
        panel.add(gameOver, "gameOver");
        panel.setSize(screenSize);

        layout.show(panel, "start");

        setContentPane(panel);
        setVisible(true);
        start.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
                layout.show(panel, "menu");
                panel.remove(start);
            }
        });
        menu.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
                controller.initMapInfo(map);
                layout.show(panel, "map");
                controller.gameStart();
                panel.remove(menu);
            }
        });
        map.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentHidden(ComponentEvent e) {
                super.componentHidden(e);
                layout.show(panel, "gameOver");
                panel.remove(map);
            }
        });
    }

    public void setController(GameController _controller) {
        controller = _controller;
        menu.setController(controller);
        map.setController(controller);
    }

    public Map getMap() {
        return this.map;
    }

}
