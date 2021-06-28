package model.locations.locationEffect;

import model.locations.Map;
import model.unit.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpecialEffect4 extends LocationEffect{
    private int id = 14;

    @Override
    public void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, List<Unit> units, Map map) {
        List<Unit> players = new ArrayList<Unit>();
        players.add(unit);

        for (Unit curUnit:units) {
            if(curUnit != unit && curUnit.getCurrentLocationId() == id){
                players.add(curUnit);
            }
        }

        if(players.size() == 1)
            return;
        Random r = new Random();
        players.get(r.nextInt(players.size())).setKnowledge(0);
    }
}
