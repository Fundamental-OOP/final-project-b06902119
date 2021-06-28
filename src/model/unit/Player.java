package model.unit;

public class Player extends Unit {
    public Player(int ID, String name, String gender, int goalMoney, int goalHappiness, int goalKnowledge) {
        super(ID, name, gender, goalMoney, goalHappiness, goalKnowledge);
    }

    @Override
    public void play() {
    }
}
