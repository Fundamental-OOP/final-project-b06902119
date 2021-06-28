package model.locations.locationEffect;

import model.locations.Map;
import model.unit.Unit;
import java.util.List;
import java.util.Random;

public class SpecialEffect8 extends LocationEffect {
    private int id = 27;
    public void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, List<Unit> units, Map map) {
        Random r = new Random();
        unit.setKnowledge(unit.getKnowledge() + r.nextInt(6)+1);
    }
}
