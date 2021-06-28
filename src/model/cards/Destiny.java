package model.cards;

import model.cards.destinyEffect.*;
import model.unit.Unit;
import model.locations.Map;

import java.util.List;

public class Destiny extends Card {
    private int id;
    private String msg;
    private int money;
    private int knowledge;
    private int happiness;
    private int stop;
    private int move;
    private DestinyEffect destinyEffect;
    private List<Unit> players;
    private Map map;

    public Destiny(int id, String msg, int money, int knowledge, int happiness, int stop, int move, DestinyEffect destinyEffect, List<Unit> players, Map map){
        this.id = id;
        this.msg = msg;
        this.money = money;
        this.knowledge = knowledge;
        this.happiness = happiness;
        this.stop = stop;
        this.move = move;
        this.destinyEffect = destinyEffect;
        this.players = players;
        this.map = map;
    }

    public Destiny(String[] config, List<Unit> players, Map map) {
        this(Integer.parseInt(config[0]),
                config[1],
                Integer.parseInt(config[2]),
                Integer.parseInt(config[3]),
                Integer.parseInt(config[4]),
                Integer.parseInt(config[5]),
                Integer.parseInt(config[6]),
                DestinyEffect.getDestinyEffect(Integer.parseInt(config[7])),
                players,
                map
        );
    }

    @Override
    public void takeEffect(Unit unit) {
        destinyEffect.takeEffect(unit, this.money, this.knowledge, this.happiness, this.stop, this.move, this.players, this.map);
    }

    @Override
    public String getDescription() {
        return msg;
    }
}
