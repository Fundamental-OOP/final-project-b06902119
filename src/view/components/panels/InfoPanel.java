package view.components.panels;

import view.GameView;
import view.utils.BlockConstraints;
import view.utils.DiamondIcon;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class InfoPanel extends JPanel{
    private JLabel title = new JLabel("", SwingConstants.CENTER);
    private JLabel img = new JLabel("", SwingConstants.LEFT);
    private JTextPane description = new JTextPane();

    public InfoPanel() {
        setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, new DiamondIcon(Color.black)));
        setBounds(GameView.screenSize.width/11*4, GameView.screenSize.height/7*4, GameView.screenSize.width/4+40, GameView.screenSize.height/5);

        setLayout(new GridBagLayout());

        title.setFont(GameView.font);

        description.setPreferredSize(new Dimension(210,140));
        description.setEditable(false);
        description.setFont(new Font("Serif", Font.BOLD, 18));
        StyledDocument doc = description.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        add(title, new BlockConstraints(0,0,3, 1));
        add(img, new BlockConstraints(0,1,1,1));
        add(description, new BlockConstraints(1,1,2,1));
    }

    public void showInfo(String path, String name, String msg, Color color) {
        setVisible(false);

        setBackground(color);
        title.setText(name);
        img.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(140, 140, java.awt.Image.SCALE_DEFAULT)));
        description.setText(msg);
        description.setBackground(color);

        setVisible(true);
    }

    public void showCardMsg(String msg) {
        // replace description only
        description.setText(msg);
    }
}
