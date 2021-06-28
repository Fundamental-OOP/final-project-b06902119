package view.components.panels;

import controller.GameController;
import view.GameView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PopupPanel extends JPanel {
    List<String> reactionList = new ArrayList<>();

    public PopupPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBounds(GameView.screenSize.width/15*6, GameView.screenSize.height/7*3, GameView.screenSize.width/5, GameView.screenSize.height/7);
    }

    public void pop(GameController controller, List<String> _reactionList, boolean interactive) {
        reactionList = _reactionList;

        removeAll();
        setLayout(new GridLayout(1, reactionList.size()));

        for (String s : reactionList) {
            JButton button = new JButton(s);
            button.setFont(GameView.font);
            button.setEnabled(interactive);
            button.addActionListener(e -> {
                setVisible(false);
                controller.doReaction(button.getText());
            });

            add(button);
        }
        setVisible(true);
    }

    public void randomReaction(GameController controller) {
        Random r = new Random();

        controller.doReaction(reactionList.get(r.nextInt(reactionList.size())));
        setVisible(false);
    }
}
