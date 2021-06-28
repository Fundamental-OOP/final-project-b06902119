package model.unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.lang.Math;

import static java.util.Arrays.asList;

public class AI extends Unit {
    public AI(int ID, String name, String gender, List<Integer> goals) {
        super(ID, name, gender, goals.get(0), goals.get(1), goals.get(2));
    }

    public AI(int ID, String name) {
        this(ID, name, genGender(), genGoal());
    }

    @Override
    public void play() {

    }


    private static List<Integer> genGoal() {
        int m = (int)(Math.random()*(23-10+1))+10;
        int h = (int)(Math.random()*(23-10+1))+10;
        int k = 50 - m - h;
        return new ArrayList<>(asList(m, h, k));
    }
    private static String genGender() {
        Random r = new Random();
        return (r.nextInt() % 2 == 0) ? "Male" : "Female";
    }
}
