package model.locations;

import model.locations.locationEffect.LocationEffect;
import model.unit.Unit;

import java.util.List;

public class Location {

	private int id;
	private String name;
	private int corner;
	private String Msg;
	private int money;
	private int knowledge;
	private int happiness;
	private int stop;
	private LocationEffect locationEffect;
	private List<Unit> players;
	private Map map;

	public Location(int id, String name, int corner, String Msg, int money, int knowledge, int happiness, int stop, LocationEffect locationEffect, List<Unit> players, Map map) {
		this.id = id;
		this.name = name;
		this.corner = corner;
		this.Msg = Msg;
		this.money = money;
		this.knowledge = knowledge;
		this.happiness = happiness;
		this.stop = stop;
		this.locationEffect = locationEffect;
		this.players = players;
		this.map = map;
	}

	public Location(String[] config, LocationEffect locationEffect, List<Unit> players, Map map) {
		this(Integer.parseInt(config[0]),
				config[1],
				Integer.parseInt(config[2]),
				config[3],
				Integer.parseInt(config[4]),
				Integer.parseInt(config[5]),
				Integer.parseInt(config[6]),
				Integer.parseInt(config[7]),
				locationEffect,
				players,
				map
		);
	}

	public void takeEffect(Unit unit) {
		locationEffect.takeEffect(unit, this.money, this.knowledge, this.happiness, this.stop, this.players, this.map);
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return Msg;
	}
}
