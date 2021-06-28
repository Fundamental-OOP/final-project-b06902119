package model.cards.chanceEffect;

import model.unit.Unit;
import model.locations.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ChanceEffect {
    private static List<ChanceEffect> chanceEffects = new ArrayList<>(Arrays.asList(new ChanceNormalEffect(), new ChanceSpecialEffect1(), new ChanceSpecialEffect2()));

    public static ChanceEffect getChanceEffect(int index) {
        return chanceEffects.get(index);
    }


    public abstract void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, List<Unit> players, Map map);
}
