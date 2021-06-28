package model.cards.chanceEffect;

import model.locations.Map;
import model.states.StepState;
import model.unit.Unit;

import java.util.List;

public class ChanceSpecialEffect1 extends ChanceEffect {
    private int id = 4;
    public void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, List<Unit> players, Map map){
        unit.state = new StepState(1, 2, unit);

    }
}
