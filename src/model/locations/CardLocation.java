package model.locations;

import model.cards.Card;
import model.locations.locationEffect.LocationEffect;
import model.unit.Unit;

import java.util.List;
import java.util.Random;

public class CardLocation extends Location {
    private static final Random r = new Random();

    private List<Card> cards;
    private Card curCard;

    public CardLocation(String[] config, LocationEffect locationEffect, List<Unit> players, Map map, List<Card> cards) {
        super(config, locationEffect, players, map);
        this.cards = cards;
    }

    @Override
    public void takeEffect(Unit unit) {
        curCard.takeEffect(unit);
    }

    public Card drawCard() {
        curCard = cards.get(r.nextInt(cards.size()));
        return curCard;
    }
}
