package model.dice;
import java.util.Random;

public class Dice {
	int point;
	public int roll() {
		Random r = new Random();
		this.point = r.nextInt(6) + 1;
		return this.point;
	}

	public int getPoint() {
		return point;
	}
}
