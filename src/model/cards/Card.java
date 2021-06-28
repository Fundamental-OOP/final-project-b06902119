package model.cards;

import model.unit.Unit;

public abstract class Card {
	public abstract void takeEffect(Unit unit);

	public abstract String getDescription();
}
