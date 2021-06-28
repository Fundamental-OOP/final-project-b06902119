package model.locations.locationEffect;

import model.locations.Map;
import model.unit.Unit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class LocationEffect {
    private static List<LocationEffect> locationEffects = new ArrayList<>(Arrays.asList(new NormalEffect(), new SpecialEffect1(), new SpecialEffect2(),
            new SpecialEffect3(), new SpecialEffect4(), new SpecialEffect5(), new SpecialEffect6(), new SpecialEffect7(), new SpecialEffect8(), new SpecialEffect9()));

    public static LocationEffect getLocationEffect(int index) {
        return locationEffects.get(index);
    }

    public abstract void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, List<Unit> units, Map map);
}
