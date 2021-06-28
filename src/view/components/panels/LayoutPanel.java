package view.components.panels;

import model.unit.Unit;
import view.GameView;
import view.components.cards.Card;
import view.components.locations.Location;
import view.utils.BlockConstraints;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LayoutPanel extends FullScreenPanel {
    private static final String configPath = "src/view/config/";
    private final List<PlayerInfoPanel> playerInfo = new ArrayList<>();
    private final List<Location> locations = new ArrayList<>();

    public LayoutPanel(InfoPanel info) {
        super(new GridBagLayout());
        readLocationConfig(info);
        readCardConfig(info);
        readPlayerConfig();
        setOpaque(false);
    }

    private void readLocationConfig(InfoPanel info) {
        try {
            File locationConfig = new File(configPath + GameView.lang + "/locationConfig");
            Scanner configReader = new Scanner(locationConfig);
            configReader.nextLine(); // skip title
            while (configReader.hasNextLine()) {
                String[] config = configReader.nextLine().replace("/n", "\n").split(",");
                Location l = new Location(Arrays.copyOfRange(config, 0, 8), info);
                locations.add(l);
                add(l, new BlockConstraints(Arrays.copyOfRange(config, 8, 12)));
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readCardConfig(InfoPanel info) {
        try {
            File locationConfig = new File(configPath + GameView.lang + "/cardConfig");
            Scanner configReader = new Scanner(locationConfig);
            configReader.nextLine(); // skip title
            while (configReader.hasNextLine()) {
                String[] config = configReader.nextLine().split(", ");
                add(new Card(Arrays.copyOfRange(config, 0, 7), info),
                        new BlockConstraints(Arrays.copyOfRange(config, 7, 11)));
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readPlayerConfig() {
        try {
            File locationConfig = new File(configPath + GameView.lang + "/playerConfig");
            Scanner configReader = new Scanner(locationConfig);
            configReader.nextLine(); // skip title

            while (configReader.hasNextLine()) {
                String[] config = configReader.nextLine().split(",");
                PlayerInfoPanel p = new PlayerInfoPanel(Arrays.copyOfRange(config, 0, 4));
                playerInfo.add(p);
                p.setVisible(false);
                add(p, new BlockConstraints(Arrays.copyOfRange(config, 4, 8)));
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setPlayerAttributes(int index, Unit unit) {
        playerInfo.get(index).setVisible(true);
        playerInfo.get(index).setAttributes(unit.getMoney(), unit.getHappiness(), unit.getKnowledge(), unit.getGoalMoney(), unit.getGoalHappiness(), unit.getGoalKnowledge(), unit.getGender());
    }

    public Location getLocation(int index) {
        return locations.get(index);
    }
    public List<Location> getLocations() {
        return locations;
    }

    public PlayerInfoPanel getPlayerInfoPanel(int index){
        return playerInfo.get(index);
    }
}
