package model.locations.locationEffect;

import model.locations.Location;
import model.locations.Map;
import model.unit.Unit;

import java.util.List;

public class SpecialEffect2 extends LocationEffect{
    private int id = 7;

    @Override
    public void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, List<Unit> units, Map map) {
        for (Location location: map.getLocations()) {
            if(location.getName().equals("德田館")){
                unit.setCurrentLocationId(location.getId());
                break;
            }
        }
    }
}