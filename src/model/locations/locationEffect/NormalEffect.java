package model.locations.locationEffect;

import model.locations.Map;
import model.states.*;
import model.unit.Unit;

import java.util.List;

public class NormalEffect extends LocationEffect {
    private int id = 0;
    public void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, List<Unit> units, Map map) {
        unit.setMoney(unit.getMoney() + money);
        unit.setKnowledge(unit.getKnowledge() + knowledge);
        unit.setHappiness(unit.getHappiness() + happiness);
        if(stop != 0)
            unit.state = new StepState(stop, 0, unit);
    }
}
