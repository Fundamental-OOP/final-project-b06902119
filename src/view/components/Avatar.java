package view.components;

import javax.swing.*;

import java.util.Objects;

import static java.awt.Image.SCALE_DEFAULT;

public class Avatar extends JLabel{

    public String path;
    public Avatar(String path){
        super(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(40, 40, SCALE_DEFAULT)));
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avatar avatar = (Avatar) o;
        return Objects.equals(path, avatar.path);
    }
}
