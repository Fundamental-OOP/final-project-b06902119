package model.cards;

import java.util.List;

import model.unit.Unit;
import model.cards.chanceEffect.*;
import model.locations.Map;

public class Chance extends Card {

    private ChanceEffect chanceEffect;

    private int id;
    private String msg;
    private int money;
    private int knowledge;
    private int happiness;
    private int stop;
    private List<Unit> players;
    private Map map;

    public Chance(int id, String Msg, int money, int knowledge, int happiness, int stop, ChanceEffect chanceEffect, List<Unit> players, Map map) {
        this.id = id;
        this.msg = Msg;
        this.money = money;
        this.knowledge = knowledge;
        this.happiness = happiness;
        this.stop = stop;
        this.chanceEffect = chanceEffect;
        this.players = players;
        this.map = map;
    }

    public Chance(String[] config, List<Unit> players, Map map) {
        this(Integer.parseInt(config[0]),
                config[1],
                Integer.parseInt(config[2]),
                Integer.parseInt(config[3]),
                Integer.parseInt(config[4]),
                Integer.parseInt(config[5]),
                ChanceEffect.getChanceEffect(Integer.parseInt(config[6])),
                players,
                map
        );
    }

    @Override
    public void takeEffect(Unit unit) {
        this.chanceEffect.takeEffect(unit, this.money, this.knowledge, this.happiness, this.stop, this.players, this.map);
    }

    @Override
    public String getDescription() {
        return msg;
    }
}
