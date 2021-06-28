package model.cards.chanceEffect;

import java.util.Random;
import model.unit.Unit;
import java.util.List;
import model.locations.Map;

public class ChanceSpecialEffect2 extends ChanceEffect {
    private int id = 6;
    public void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, List<Unit> players, Map map){
        Random r = new Random();
        unit.setCurrentLocationId(r.nextInt(map.getLocations().size()));
    }
}
