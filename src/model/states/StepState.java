package model.states;

import model.unit.Unit;

public class StepState {

    public double round;
    public int stepSize;
    public Unit unit;

    public StepState(double round, int stepSize, Unit unit){
        this.round = round;
        this.stepSize = stepSize;
        this.unit = unit;
    }

    public void takeEffect() {
    }
}
