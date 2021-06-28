package model.locations.locationEffect;

import model.locations.Map;
import model.states.StepState;
import model.unit.Unit;

import java.util.List;

public class SpecialEffect5 extends LocationEffect{
    private int id = 16;

    @Override
    public void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, List<Unit> units, Map map) {

        if(map.getReaction().equals("YES")){
            unit.setMoney(unit.getMoney() - 3);
            unit.setHappiness(unit.getHappiness() + 5);
            unit.setKnowledge(unit.getKnowledge() + 2);
            unit.state = new StepState(3, 0, unit);
        }
        else{
            unit.setHappiness(unit.getHappiness() - 1);
        }
    }
}