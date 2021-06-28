package view.components.cards;

import view.GameView;
import view.components.panels.InfoPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Card extends JButton implements ActionListener {

    private final String imgPath;
    private final String name;
    private final List<String> msg = new ArrayList<>();
    private final Color color;
    public InfoPanel info;

    public Card(String name, int w, int h, String path, int R, int G, int B, InfoPanel info) {
        this.name = name;
        imgPath = path;
        this.info = info;

        setLayout(new GridLayout(2,1));

        JLabel label = new JLabel(name, CENTER);
        label.setFont(GameView.font);

        setPreferredSize(new Dimension(w, h));
        setOpaque(true);
        color = new Color(R,G,B);
        setBackground(color);

        setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_DEFAULT)));
        add(label);
        addActionListener(this);

        setMsg();
    }
    public Card(String[] config, InfoPanel info) {
        this(config[0], Integer.parseInt(config[1]), Integer.parseInt(config[2]), "src/images/locations/"+config[3], Integer.parseInt(config[4]), Integer.parseInt(config[5]), Integer.parseInt(config[6]), info);
    }

    private void setMsg(){
        try {
            File locationConfig = new File("src/view/config/zh/idioms");
            Scanner configReader = new Scanner(locationConfig);
            while (configReader.hasNextLine()) {
                String config = configReader.nextLine().replace("/n", "\n");
                msg.add(config);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        info.showInfo(imgPath, name, msg.get((int)(Math.random()*1000%101)), color);
    }
}