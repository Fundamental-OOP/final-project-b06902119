package view.components;

import controller.GameController;
import view.components.panels.FullScreenPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Dice extends FullScreenPanel implements ActionListener {
    private final DiceButton dice;
    private final Random r = new Random();
    private final int animateCount = 20; // count * delay = time (ms)
    private final Timer animateTM = new Timer(50, this);
    private int timerCount = 0;

    private GameController controller;
    public Dice(String path) {
        super(new GridBagLayout());

        dice = new DiceButton(path);
        dice.addActionListener(this);
        add(dice);

        setOpaque(false);
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public void triggerRollDice() {
        dice.setEnabled(true);
        dice.doClick();
        dice.setEnabled(false);
    }

    public void render(int point) {
        dice.paintPoint(point);
    }

    public void lock() {
        dice.setEnabled(false);
    }

    public void unlock() {
        dice.setEnabled(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) { // roll dice animation
        dice.paintPoint(r.nextInt(6)+1);
        if (animateTM.isRunning()) {
            timerCount++;
            if (timerCount >= animateCount) {
                animateTM.stop();
                controller.rollDice(this);
            }
        } else {
            lock();
            timerCount = 0;
            animateTM.start();
        }
    }

    private class DiceButton extends JButton {
        private final int width = 64, height = 64;
        private final String diceImgPath;
        private final ImageIcon[] iconList = new ImageIcon[6];

        private int point = 1;
        DiceButton(String imgPath) {
            diceImgPath = imgPath;
            loadImages();
            setPreferredSize(new Dimension(width, height));
        }

        void loadImages() {
            for (int i = 0; i < 6; i++)
                iconList[i] = new ImageIcon(diceImgPath + "/" + (i+1) + ".png");
        }

        void paintPoint(int p) {
            this.point = p;
            repaint();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            iconList[point-1].paintIcon(this, g, 0, 0);
        }
    }
}
