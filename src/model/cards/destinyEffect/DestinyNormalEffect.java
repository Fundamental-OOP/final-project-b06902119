package model.cards.destinyEffect;

import model.states.StepState;
import model.unit.Unit;
import model.locations.Map;
import java.util.List;

public class DestinyNormalEffect extends DestinyEffect{
    private int id = 0;
    public void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, int move, List<Unit> players, Map map){
        unit.setMoney(unit.getMoney() + money);
        unit.setKnowledge(unit.getKnowledge() + knowledge);
        unit.setHappiness(unit.getHappiness() + happiness);
        if(stop != 0)
            unit.state = new StepState(stop, 0, unit);
        unit.setCurrentLocationId(unit.getCurrentLocationId() + move);
    }
}
