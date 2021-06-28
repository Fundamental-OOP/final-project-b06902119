package model.cards.chanceEffect;

import model.states.StepState;
import model.unit.Unit;
import model.locations.Map;
import java.util.List;

public class ChanceNormalEffect extends ChanceEffect {
    private int id = 0;
    public void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, List<Unit> players, Map map){
        unit.setMoney(unit.getMoney() + money);
        unit.setKnowledge(unit.getKnowledge() + knowledge);
        unit.setHappiness(unit.getHappiness() + happiness);
        if(stop != 0)
            unit.state = new StepState(stop, 0, unit);
    }
}
