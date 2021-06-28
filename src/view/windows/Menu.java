package view.windows;

import controller.GameController;

import view.GameView;

import view.components.Background;
import view.components.panels.LifePlanPanel;
import view.components.Logo;
import view.components.panels.PlayerCountPanel;

import view.utils.ColorTool;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Menu extends JPanel {

    private final CardLayout layout = new CardLayout();
    private final JPanel cardPane = new JPanel(layout);
    private final List<LifePlanPanel> playerList = new ArrayList<>();
    private PlayerCountPanel pcp;
    private int currentCard = 0;
    private final JButton next = new JButton("Next");
    private GameController controller;

    public Menu() {
        setBackground(new Color(0x97acc2));
        next.setFont(GameView.font);

        pcp = new PlayerCountPanel(next);
        cardPane.add(pcp , "playerCount" );

        for (int i = 0; i < 4; i++) {
            playerList.add(new LifePlanPanel("Player" + (i+1), GameView.avatars.get(i), next));
            cardPane.add( playerList.get(i) );
        }

        initLayeredPane();
    }

    private void initLayeredPane() {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(GameView.screenSize);

        Background background = new Background("src/images/background.png");
        layeredPane.add(background, 0);

        Logo logo = new Logo("src/images/logo.png");
        logo.setLocation(GameView.screenSize.width/5*2, GameView.screenSize.height/15*2);
        layeredPane.add(logo,0);

        cardPane.setBackground(ColorTool.adjustAlpha(Color.white, 75));
        cardPane.setBounds(GameView.screenSize.width/3, GameView.screenSize.height/3, GameView.screenSize.width/3, GameView.screenSize.height/3);
        cardPane.setBorder(BorderFactory.createLineBorder(Color.black));
        layeredPane.add(cardPane, 0 );

        //config next button
        next.setBounds(GameView.screenSize.width/3, GameView.screenSize.height/3*2, GameView.screenSize.width/3, GameView.screenSize.height/15);
        next.setBackground(ColorTool.adjustAlpha(Color.LIGHT_GRAY, 75));
        next.setOpaque(true);
        next.setEnabled(false);
        next.addActionListener(e -> {
            for (LifePlanPanel player: playerList) {
                if (player.getMoneyPlan() + player.getHappinessPlan() + player.getKnowledgePlan() != 50){
                    return;
                }
            }
            if (currentCard == pcp.getHumanPlayerCount()-1) {
                next.setText("Play!");
                currentCard++;
                layout.next(cardPane);
            }
            else if (currentCard == pcp.getHumanPlayerCount()) { // Game start
                controller.setPlayer(this);
                setVisible(false);
            }
            else {
                currentCard++;
                layout.next(cardPane);
            }
            next.setEnabled(false);
        });
        layeredPane.add(next, 0 );

        this.add(layeredPane);
    }

    public void setController(GameController _controller) {
        controller = _controller;
    }

    public int getPlayerCount(){
        return pcp.getPlayerCount();
    }

    public int getHumanPlayerCount(){
        return pcp.getHumanPlayerCount();
    }

    public LifePlanPanel getPlayerPlan(int id){
        return playerList.get(id);
    }
}
