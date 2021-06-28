package model.locations.locationEffect;

import model.locations.Map;
import model.unit.Unit;

import java.util.ArrayList;
import java.util.List;

public class SpecialEffect9 extends LocationEffect {
    private int id = 31;
    public void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, List<Unit> units, Map map) {
        List<Unit> curUnits = new ArrayList<>();
        for (Unit curUnit:units) {
            if(curUnit.getCurrentLocationId() == unit.getCurrentLocationId())
                curUnits.add(curUnit);
        }
        if(curUnits.size() >= 2){
            int maxMoney = -1;
            for (Unit curUnit: curUnits) {
                if(maxMoney < curUnit.getMoney())
                    maxMoney = curUnit.getMoney();
            }
            for (Unit curUnit: curUnits) {
                if(maxMoney == curUnit.getMoney()) {
                    curUnit.setMoney(curUnit.getMoney() - 2);
                }
                else{
                    curUnit.setHappiness(curUnit.getHappiness() + 1);
                }
            }
        }
    }
}
