package view.components.locations;

import view.GameView;
import view.components.Avatar;
import view.components.panels.InfoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Location extends JButton implements ActionListener {

    private String imgPath;
    private String name;
    private final String msg;
    private InfoPanel info;
    private Color color;


    public Location(String name, int w, int h, String msg, String path, Color color, InfoPanel info) {
        this.name = name;
        this.msg = msg;
        this.info = info;
        this.color = color;
        imgPath = path;

        setBackground(color);
        setText(name);
        setFont(GameView.font);
        setOpaque(true);
        setPreferredSize(new Dimension(w, h));
        addActionListener(this);
        setLayout(new GridLayout(2,2));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public Location(String[] config, InfoPanel info) {
        this(config[0], Integer.parseInt(config[1]), Integer.parseInt(config[2]), config[3], "src/images/locations/"+config[4], new Color(Integer.parseInt(config[5]),Integer.parseInt(config[6]),Integer.parseInt(config[7])), info);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        info.showInfo(imgPath, name, msg, color);
    }

    @Override
    public String getName() {
        return name;
    }

    public void addAvatar(Avatar avatar) {
        if (getComponentCount() % 2 == 0) {
            avatar.setHorizontalAlignment(SwingConstants.LEFT);
        }
        else {
            avatar.setHorizontalAlignment(SwingConstants.RIGHT);
        }
        add(avatar);
    }
}
