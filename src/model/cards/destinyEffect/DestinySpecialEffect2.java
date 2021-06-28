package model.cards.destinyEffect;

import model.locations.*;
import model.unit.Unit;


import java.util.List;

public class DestinySpecialEffect2  extends DestinyEffect{
    private int id = 8;
    public void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, int move, List<Unit> players, Map map){
        for (Location location: map.getLocations()) {
            if(location.getName().equals("總圖")){
                unit.setCurrentLocationId(location.getId());
            }
        }
    }
}
