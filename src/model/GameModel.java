package model;

import controller.GameController;

import model.cards.*;
import model.dice.Dice;
import model.locations.*;
import model.locations.locationEffect.*;
import model.states.StepState;
import model.unit.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.File;

public class GameModel {
    private List<Unit> units = new ArrayList<>();
    private Map map = new Map();
    private List<Card> chances = new ArrayList<>();
    private List<Card> destinies = new ArrayList<>();
    private Dice dice = new Dice();

    private int curUnitIndex = -1;

    private GameController controller;

    public GameModel() {
        loadChances();
        loadDestinies();
        loadMap();
    }

    public void setController(GameController _controller) {
        controller = _controller;
    }

    public void addUnit(Unit newUnit) {
        units.add(newUnit);
    }

    private void loadChances() {
        try {
            Scanner sc = new Scanner(new File("src/model/config/zh/chance"));
            sc.nextLine();

            while (sc.hasNextLine()) {
                String[] config = sc.nextLine().split(", ");
                Chance chance = new Chance(config, units, map);
                chances.add(chance);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadDestinies() {
        try {
            Scanner sc = new Scanner(new File("src/model/config/zh/destiny"));
            sc.nextLine();

            while (sc.hasNextLine()) {
                String[] config = sc.nextLine().split(", ");
                Destiny destiny = new Destiny(config, units, map);
                destinies.add(destiny);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadMap() {
        LocationEffect takeChanceEffect = new GetCardEffect(chances);
        LocationEffect takeDestinyEffect = new GetCardEffect(destinies);

        try {
            Scanner sc = new Scanner(new File("src/model/config/zh/location"));
            sc.nextLine();

            while (sc.hasNextLine()) {
                String[] config = sc.nextLine().split(", ");
                Location location;

                int typeFlag = Integer.parseInt(config[8]);
                switch (typeFlag) {
                    case -1:
                        location = new CardLocation(config, takeChanceEffect, units, map, chances);
                        break;
                    case -2:
                        location = new CardLocation(config, takeDestinyEffect, units, map, destinies);
                        break;
                    default:
                        location = new Location(config, LocationEffect.getLocationEffect(typeFlag), units, map);
                }
                map.addLocation(location);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Unit> getUnits() {
        return units;
    }

    public Unit getCurUnit() {
        if (curUnitIndex >= 0 && curUnitIndex < units.size())
            return units.get(curUnitIndex);
        return null;
    }

    public int nextPlayer() {
        if (curUnitIndex >= 0 && curUnitIndex < units.size()) {
            Unit curUnit = getCurUnit();
            curUnit.state.round--;
            if(curUnit.state.round < 0)
                curUnit.state = new StepState(Double.POSITIVE_INFINITY, 1, curUnit);
        }

        curUnitIndex = (curUnitIndex + 1) % units.size();
        return curUnitIndex;
    }

    public int rollDice() {
        int point = dice.roll();
        getCurUnit().step(point);
        return point;
    }

    public Card getCard() {
        Location to = map.getLocation(getCurUnit().getCurrentLocationId());
        if (to instanceof CardLocation) {
            Card card = ((CardLocation) to).drawCard();
            return card;
        }
        return null;
    }


    public void takeLocationEffect(String reaction) {
        Unit curUnit = getCurUnit();
        map.setReaction(reaction);
        map.getLocation(curUnit.getCurrentLocationId()).takeEffect(curUnit);
    }

    public boolean playerCanMove() {
        return !(getCurUnit().state.stepSize == 0);
    }

    public boolean gameOver() {
        return getCurUnit().reachGoals();
    }
}
