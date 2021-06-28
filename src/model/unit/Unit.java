package model.unit;

import model.cards.Card;
import model.dice.Dice;
import model.states.StepState;

import java.util.List;
import java.util.Random;

public abstract class Unit {

	private final int ID;
	private final String name;
	private final String gender;
	private final int goalMoney;
	private final int goalHappiness;
	private final int goalKnowledge;

	private int money;
	private int happiness;
	private int knowledge;
	private List<Card> chances;
	private List<Card> destinies;
	private int currentLocationId;
	private Dice dice;

	public StepState state = new StepState(Double.POSITIVE_INFINITY, 1, this);

	public Unit(int ID, String name, String gender, int goalMoney, int goalHappiness, int goalKnowledge) {
		this.ID = ID;
		this.name = name;
		this.gender = gender;
		this.goalMoney = goalMoney;
		this.goalHappiness = goalHappiness;
		this.goalKnowledge = goalKnowledge;

		this.money = 5;
		this.happiness = 5;
		this.knowledge = 5;
		this.currentLocationId = 0;
	}


	public void setMoney(int money) {
		this.money = money;
	}
	public void setHappiness(int happiness) {
		this.happiness = happiness;
	}
	public void setKnowledge(int knowledge){
		this.knowledge = knowledge;
	}

	public void setCurrentLocationId(int LocationId){
		this.currentLocationId = LocationId;
	}
	public void setStepSize(int stepSize){
		this.state.stepSize = stepSize;
	}
	public void setRound(double round){
		this.state.round = round;
	}

	public int getID() {
		return this.ID;
	}
	public String getName(){
		return this.name;
	}
	public String getGender(){
		return this.gender;
	}
	public int getMoney() {
		return this.money;
	}
	public int getHappiness() {
		return this.happiness;
	}
	public int getKnowledge(){
		return this.knowledge;
	}
	public int getGoalMoney(){
		return this.goalMoney;
	}
	public int getGoalHappiness(){
		return this.goalHappiness;
	}
	public int getGoalKnowledge() {
		return this.goalKnowledge;
	}
	public int getCurrentLocationId(){
		return this.currentLocationId;
	}
	public int getStepSize(){
		return this.state.stepSize;
	}
	public double getRound(){
		return this.state.round;
	}


	public void takeChance(){
		Random r = new Random();
		int chanceIndex = r.nextInt(chances.size());
		chances.get(chanceIndex).takeEffect(this);
		//TODO: send Msg back
	}
	public void takeDestiny() {
		Random r = new Random();
		int destinyIndex = r.nextInt(destinies.size());
		chances.get(destinyIndex).takeEffect(this);
		//TODO: send Msg back
	}

	public int rollDice() {
		return (int) (Math.random()*6)+1;
	}

	public int step(int point) {
		currentLocationId = (currentLocationId + (point * state.stepSize)) % 32; // TODO: 32 -> mapSize
		return currentLocationId;
	}

	public int getPoint(){
		return dice.getPoint();
	}

	public abstract void play();

	public boolean reachGoals() {
		return money >= goalMoney && happiness >= goalHappiness && knowledge >= goalKnowledge;
	}
}