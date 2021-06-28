package view.windows;

import controller.GameController;
import model.unit.Unit;

import view.GameView;

import view.components.*;
import view.components.locations.Location;
import view.components.panels.InfoPanel;
import view.components.panels.LayoutPanel;
import view.components.panels.PopupPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static view.GameView.screenSize;

public class Map extends JPanel {
    private final JLayeredPane layeredPane;

    private final Background background;
    private final LayoutPanel layoutPanel;
    private final Dice dice;
    private final Logo logo;
    private InfoPanel info;
    private PopupPanel popup;

    private GameController controller;

    public Map() {
        layeredPane = new JLayeredPane();
        info = new InfoPanel();
        popup = new PopupPanel();
        background = new Background("src/images/background.png");
        layoutPanel = new LayoutPanel(info);
        dice = new Dice("src/images/dice");
        logo = new Logo("src/images/logo.png");
        info.setVisible(false);
        popup.setVisible(false);

        initLayeredPane();
        setBackground(new Color(0x97acc2)); // black mage
    }

    private void initLayeredPane() {
        layeredPane.setPreferredSize(screenSize);
        layeredPane.add(background, 0);

        logo.setLocation(GameView.screenSize.width/5*2, GameView.screenSize.height/5);
        layeredPane.add(logo,0);

        layeredPane.add(layoutPanel, 0);
        layeredPane.add(dice, 0);
        layeredPane.add(info, 0);
        layeredPane.add(popup, 0);
        this.add(layeredPane);
    }

    public void initPlayersInfo(List<Unit> units) {
        // playerInfo
        for (int i = 0; i < units.size(); i++) {
            layoutPanel.getLocation(0).addAvatar(GameView.avatars.get(units.get(i).getID()));
            layoutPanel.setPlayerAttributes(i, units.get(i));
        }
    }

    public void setController(GameController _controller) {
        this.controller = _controller;
        dice.setController(_controller);
    }


    public void updatePlayerLocation(List<Unit> units, Unit curUnit) {

        List<Location> locations = layoutPanel.getLocations();

        for (Location l: locations) {
            l.removeAll();
            l.revalidate();
            l.repaint();
        }

        for (Unit u: units) {
            Location to = layoutPanel.getLocation(u.getCurrentLocationId());
            to.addAvatar(GameView.avatars.get(u.getID()));
            to.revalidate();
            to.repaint();
        }
    }

    public void popupReaction(Unit curUnit, boolean interactive) {
        try {
            layoutPanel.getLocation(curUnit.getCurrentLocationId()).doClick();
            if (layoutPanel.getLocation(curUnit.getCurrentLocationId()).getName().equals("森多概")) {
                popup.pop(controller, Arrays.asList("YES", "NO"), interactive);
            }
            else {
                popup.pop(controller, Arrays.asList("OK"), interactive);
            }
        }
        catch(Exception e){
            popup.pop(controller, Arrays.asList("OK"), interactive);
        }
    }

    public void updatePlayerInfo(List<Unit> units) {
        for (Unit unit: units) {
            layoutPanel.setPlayerAttributes(unit.getID(), unit);
        }
    }

    public void setUnitHighlight(int id, boolean highlight){
        layoutPanel.getPlayerInfoPanel(id).setHighlight(highlight);
    }

    public void showMsg(String imgPath, String title, String msg){
        info.showInfo(imgPath, title, msg, Color.white);
    }

    public void showCardDescription(String msg) {
        info.showCardMsg(msg);
    }

    public void unlockDice() {
        dice.unlock();
    }

    public void triggerRollDice() {
        dice.triggerRollDice();
    }

    public void triggerReaction() {
        popup.randomReaction(controller);
    }
}