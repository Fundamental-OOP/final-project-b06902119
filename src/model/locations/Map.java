package model.locations;

import java.util.ArrayList;
import java.util.List;

public class Map {
	private List<Location> locations;
	private String reaction;

	public Map() {
		locations = new ArrayList<>();
	}

	public List<Location> getLocations() {
		return locations;
	}
	public Location getLocation(int index) {
		return locations.get(index);
	}

	public void addLocation(Location location) {
		locations.add(location);
	}

	public void setReaction(String reaction) {
		this.reaction = reaction;
	}

	public String getReaction() {
		return reaction;
	}
}
