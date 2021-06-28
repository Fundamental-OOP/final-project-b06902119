package model.cards.destinyEffect;

import model.unit.Unit;
import model.locations.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DestinyEffect {
    private static List<DestinyEffect> destinyEffects = new ArrayList<>(Arrays.asList(new DestinyNormalEffect(), new DestinySpecialEffect1(), new DestinySpecialEffect2(), new DestinySpecialEffect3()));

    public static DestinyEffect getDestinyEffect(int index) {
        return destinyEffects.get(index);
    }

    public abstract void takeEffect(Unit unit, int money, int knowledge, int happiness, int move, int stop, List<Unit> players, Map map);
}
