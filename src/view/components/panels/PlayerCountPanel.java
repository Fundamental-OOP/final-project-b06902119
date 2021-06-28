package view.components.panels;

import view.GameView;

import javax.swing.*;
import java.awt.*;

public class PlayerCountPanel extends JPanel {

    private final JLabel playerCountLabel = new JLabel("Number of players:");
    private final JLabel humanPlayerCountLabel = new JLabel("Number of human players:");
    private final JComboBox<String> playerCountSlider = new JComboBox<>();
    private final JComboBox<String> humanPlayerCountSlider = new JComboBox<>();
    private int playerCount;
    private int humanPlayerCount;

    public PlayerCountPanel(JButton next) {

        //config setting panel
        setLayout(new GridLayout(4,1));

        //config labels
        playerCountLabel.setFont(GameView.font);
        humanPlayerCountLabel.setFont(GameView.font);

        //config selection box
        playerCountSlider.addItem("Select number of players");
        playerCountSlider.addItem("2");
        playerCountSlider.addItem("3");
        playerCountSlider.addItem("4");
        playerCountSlider.setFont(GameView.font);
        playerCountSlider.addActionListener(e -> {
            JComboBox cb = (JComboBox) e.getSource();
            String count = (String) cb.getSelectedItem();
            humanPlayerCountSlider.removeAllItems();
            humanPlayerCountSlider.addItem("Select number of human players");
            if (count != null && !count.equals("Select number of players")){
                for (int i = 1; i <= Integer.parseInt(count); i++) {
                    humanPlayerCountSlider.addItem(Integer.toString(i));
                }
                playerCount = Integer.parseInt(count);
            }
            next.setEnabled(false);
        });

        humanPlayerCountSlider.addItem("Select number of human players");
        humanPlayerCountSlider.setFont(GameView.font);
        humanPlayerCountSlider.addActionListener(e -> {
            JComboBox cb = (JComboBox) e.getSource();
            String count = (String) cb.getSelectedItem();
            next.setEnabled(count != null && !count.equals("Select number of human players"));
            if (count != null && !count.equals("Select number of human players")){
                humanPlayerCount = Integer.parseInt(count);
            }
        });

        //add components
        add(playerCountLabel);
        add(playerCountSlider);
        add(humanPlayerCountLabel);
        add(humanPlayerCountSlider);
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public int getHumanPlayerCount(){
        return humanPlayerCount;
    }
}