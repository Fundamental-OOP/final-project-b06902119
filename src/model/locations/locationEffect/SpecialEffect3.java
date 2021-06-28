package model.locations.locationEffect;

import model.locations.Map;
import model.unit.Unit;

import java.util.List;

public class SpecialEffect3 extends LocationEffect{
    private int id = 11;

    @Override
    public void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, List<Unit> units, Map map) {
        unit.takeChance();
    }
}