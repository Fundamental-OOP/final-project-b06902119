package model.cards.destinyEffect;

import model.locations.Map;
import model.states.StepState;
import model.unit.Unit;

import java.util.List;

public class DestinySpecialEffect1  extends DestinyEffect{
    private int id = 6;
    public void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, int move, List<Unit> players, Map map){
        unit.setMoney(unit.getMoney() - 10);
        if(unit.getMoney() < 0)
            unit.state = new StepState(Double.POSITIVE_INFINITY, 0, unit);
    }
}
