package view.components.panels;

import view.GameView;
import view.utils.BlockConstraints;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LifePlanPanel extends JPanel {
    private JLabel name;
    private JLabel avatar;
    private AttributePane moneyPane = new AttributePane("src/images/attributes/money.png", "Money:      ", 20);
    private AttributePane happinessPane = new AttributePane("src/images/attributes/happiness.png", "Happiness: ", 15);
    private AttributePane knowledgePane = new AttributePane("src/images/attributes/knowledge.png", "Knowledge: ", 15);
    private JLabel genderImg = new JLabel(new ImageIcon(new ImageIcon("src/images/attributes/gender.png").getImage().getScaledInstance(GameView.screenSize.width/20, GameView.screenSize.height/15, java.awt.Image.SCALE_DEFAULT)));
    private JComboBox<String> genderBox = new JComboBox<>();

    public LifePlanPanel(String name, JLabel avatar, JButton next){

        super(new GridBagLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.avatar = new JLabel(avatar.getIcon(), SwingConstants.CENTER);

        this.name = new JLabel(name + "'s Plan: (Total should be 50)");
        this.name.setFont(GameView.font);

        genderBox.setFont(GameView.font);
        genderBox.addItem("Select player's gender");
        genderBox.addItem("Male");
        genderBox.addItem("Female");
        genderBox.addActionListener(e -> {
            JComboBox cb = (JComboBox) e.getSource();
            String count = (String) cb.getSelectedItem();
            next.setEnabled(count != null && !count.equals("Select player's gender"));
        });

        add(this.avatar, new BlockConstraints(0, 0, 1, 1));
        add(this.name, new BlockConstraints(1, 0, 1, 1));
        add(Box.createHorizontalGlue());
        add(genderImg, new BlockConstraints(0, 1, 1, 1));
        add(genderBox, new BlockConstraints(1, 1, 2,1));
        add(moneyPane, new BlockConstraints(0, 2, 3, 1));
        add(happinessPane, new BlockConstraints(0, 3, 3, 1));
        add(knowledgePane, new BlockConstraints(0, 4, 3, 1));
    }

    private class AttributePane extends JPanel{

        protected int value;

        AttributePane(String path, String label, int defaultValue){
            super(new GridBagLayout());
            JLabel img = new JLabel(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(GameView.screenSize.width/20, GameView.screenSize.height/15, java.awt.Image.SCALE_DEFAULT)));
            JLabel item = new JLabel(label);
            JTextField text = new JTextField(Integer.toString(defaultValue));
            JSlider slider = new JSlider(0, 50, defaultValue);
            value = defaultValue;

            item.setFont(GameView.font);
            // numbers only
            ((AbstractDocument)text.getDocument()).setDocumentFilter(new DocumentFilter(){
                final Pattern regEx = Pattern.compile("\\d*");

                @Override
                public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                    Matcher matcher = regEx.matcher(text);
                    if(!matcher.matches()){
                        return;
                    }
                    super.replace(fb, offset, length, text, attrs);
                }
            });
            text.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    JTextField tf = (JTextField) e.getSource();
                    int val = Integer.parseInt(tf.getText());
                    if (val >= 0 && val <= 50){
                        value = val;
                        slider.setValue(val);
                    }
                }
            });

            slider.setMajorTickSpacing(5);
            slider.setMinorTickSpacing(1);
            slider.setSnapToTicks(true);
            slider.setPaintTicks(true);
            slider.setPaintTrack(true);
            slider.setPaintLabels(true);
            slider.addChangeListener(e -> {
                JSlider s = (JSlider) e.getSource();
                value = s.getValue();
                text.setText(Integer.toString(value));
            });

            add(img, new BlockConstraints(0, 0, 1, 1));
            add(item, new BlockConstraints(1, 0, 1, 1));
            add(text, new BlockConstraints(2, 0, 1, 1));
            add(slider, new BlockConstraints(3, 0, 2, 1));
        }
    }

    public int getMoneyPlan(){
        return moneyPane.value;
    }

    public int getKnowledgePlan(){
        return knowledgePane.value;
    }

    public int getHappinessPlan(){
        return happinessPane.value;
    }

    public String getGender(){
        return (String)genderBox.getSelectedItem();
    }
}
