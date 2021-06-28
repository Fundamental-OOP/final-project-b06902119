package model.locations.locationEffect;

import model.locations.Map;
import model.unit.Unit;

import java.util.List;
import java.util.Random;

public class SpecialEffect1 extends LocationEffect{
    private int id = 3;

    @Override
    public void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, List<Unit> units, Map map) {
        Random r = new Random();
        unit.setMoney(unit.getMoney() + r.nextInt(6)+1);
    }
}
