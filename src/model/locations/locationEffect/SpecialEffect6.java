package model.locations.locationEffect;

import model.locations.*;
import model.unit.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpecialEffect6 extends LocationEffect {
    private int id = 17;
    public void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, List<Unit> units, Map map) {
        List<Unit> candidates = new ArrayList<>();
        for (Unit curUnit: units) {
            if(!curUnit.getGender().equals(unit.getGender())){
                candidates.add(curUnit);
            }
        }

        if(candidates.size() == 0)
            return;

        Random r = new Random();
        Unit target = candidates.get(r.nextInt(candidates.size()));

        for (Location location: map.getLocations()) {
            if(location.getName().equals("大一女")){
                target.setCurrentLocationId(location.getId());
            }
        }
    }
}
