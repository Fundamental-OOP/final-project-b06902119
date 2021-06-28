package view.utils;

import java.awt.*;

public class BlockConstraints extends GridBagConstraints {

    public BlockConstraints(int gridx, int gridy, int gridwidth, int gridheight, int ipadx, int ipady) {
        super(gridx, gridy, gridwidth, gridheight, 0.5, 0.5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), ipadx, ipady);
    }

    public BlockConstraints(int gridx, int gridy, int gridwidth, int gridheight) {
        super(gridx, gridy, gridwidth, gridheight, 0.5, 0.5, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
    }
    public BlockConstraints(String[] config) {
        this(Integer.parseInt(config[0]), Integer.parseInt(config[1]),
                Integer.parseInt(config[2]), Integer.parseInt(config[3]));
    }
}
