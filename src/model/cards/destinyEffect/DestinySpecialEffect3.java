package model.cards.destinyEffect;

import model.locations.Map;
import model.unit.Unit;

import java.util.List;

public class DestinySpecialEffect3  extends DestinyEffect{
    private int id = 19;
    public void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, int move, List<Unit> units, Map map){
        unit.setMoney(unit.getMoney() - 3);
        for (Unit otherUnit: units) {
            if(otherUnit != unit){
                otherUnit.setMoney(otherUnit.getMoney() + 1);
            }
        }
    }
}
