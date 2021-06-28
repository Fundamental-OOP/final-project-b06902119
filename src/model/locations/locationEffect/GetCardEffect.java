package model.locations.locationEffect;

import model.cards.Card;
import model.locations.Map;
import model.unit.Unit;

import java.util.List;
import java.util.Random;

public class GetCardEffect extends LocationEffect {
    private static final Random r = new Random();

    private List<Card> cards;

    public GetCardEffect(List<Card> _cards) {
        this.cards = _cards;
    }

    public void takeEffect(Unit unit, int money, int knowledge, int happiness, int stop, List<Unit> units, Map map){
        cards.get(r.nextInt(cards.size())).takeEffect(unit);
    }
}