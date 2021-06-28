package view.components.panels;

import view.GameView;
import view.utils.BlockConstraints;
import view.utils.DiamondIcon;
import javax.swing.*;
import java.awt.*;

public class PlayerInfoPanel extends JPanel {

    JLabel name, money, happiness, knowledge, gender, avatar;
    JLabel moneyImg = new JLabel(new ImageIcon(new ImageIcon("src/images/attributes/money.png").getImage().getScaledInstance(GameView.screenSize.width/40, GameView.screenSize.height/30, java.awt.Image.SCALE_DEFAULT)));
    JLabel happinessImg = new JLabel(new ImageIcon(new ImageIcon("src/images/attributes/happiness.png").getImage().getScaledInstance(GameView.screenSize.width/40, GameView.screenSize.height/30, java.awt.Image.SCALE_DEFAULT)));
    JLabel knowledgeImg = new JLabel(new ImageIcon(new ImageIcon("src/images/attributes/knowledge.png").getImage().getScaledInstance(GameView.screenSize.width/40, GameView.screenSize.height/30, java.awt.Image.SCALE_DEFAULT)));
    JLabel genderImg = new JLabel(new ImageIcon(new ImageIcon("src/images/attributes/gender.png").getImage().getScaledInstance(GameView.screenSize.width/40, GameView.screenSize.height/30, java.awt.Image.SCALE_DEFAULT)));

    public PlayerInfoPanel(String name, int w, int h, String avatar) {
        this.name = new JLabel(name);
        this.money = new JLabel("20");
        this.happiness = new JLabel("15");
        this.knowledge = new JLabel("15");
        this.gender = new JLabel("Male");
        this.name.setFont(GameView.font);
        this.avatar = new JLabel(new ImageIcon(new ImageIcon(avatar).getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_DEFAULT)), SwingConstants.CENTER);

        setBorder(BorderFactory.createMatteBorder(10, 10, 5, 10, new DiamondIcon(Color.black)));
        setFont(GameView.font);
        setPreferredSize(new Dimension(w, h));
        setBackground(new Color(242, 188, 131));
        setLayout(new GridBagLayout());

        add(this.avatar, new BlockConstraints(0,0,1,1));
        add(this.name, new BlockConstraints(1,0,3,1));
        add(moneyImg, new BlockConstraints(0,1,1,1));
        add(money, new BlockConstraints(1,1,1,1));
        add(genderImg, new BlockConstraints(2,1,1,1));
        add(gender, new BlockConstraints(3,1,1,1));
        add(happinessImg, new BlockConstraints(0,2,1,1));
        add(happiness, new BlockConstraints(1,2,1,1));
        add(Box.createHorizontalGlue());
        add(knowledgeImg, new BlockConstraints(0,3,1,1));
        add(knowledge, new BlockConstraints(1,3,1,1));
        add(Box.createHorizontalGlue());
    }

    public PlayerInfoPanel(String[] config) {
        this(config[0], Integer.parseInt(config[1]), Integer.parseInt(config[2]), "src/images/avatars/"+config[3]);
    }

    public void setAttributes(int money, int happiness, int knowledge, int moneyGoal, int happinessGoal, int knowledgeGoal, String gender){
        this.money.setText(money + "/" + moneyGoal);
        this.knowledge.setText(knowledge + "/" + knowledgeGoal);
        this.happiness.setText(happiness + "/" + happinessGoal);

        if (gender.equals("Male") | gender.equals("Female")){
            this.gender.setText(gender);
        }
    }

    public void setHighlight(boolean highlight){
        if (highlight){
            setBackground(Color.WHITE);
            setBorder(BorderFactory.createMatteBorder(10, 10, 5, 10, new DiamondIcon(Color.red)));
        }
        else{
            setBackground(new Color(242, 188, 131));
            setBorder(BorderFactory.createMatteBorder(10, 10, 5, 10, new DiamondIcon(Color.black)));
        }
    }
}
