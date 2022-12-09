package org.co2dice.mirai.bean.dice;


import java.util.ArrayList;
import java.util.List;

public class CheatDice extends Dice{
    private int minValue;
    private int maxValue;

    public CheatDice(int diceNum, int minValue, int maxValue) {
        super(diceNum);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public int getDiceMin() {
        return super.getDiceMin();
    }

    @Override
    public void setDiceMin(int minValue) {
        this.minValue = minValue;
    }

    @Override
    public List<Integer> getDiceNumArray() {
        List<Integer> list = new ArrayList<>();
        for (int i = minValue; i <= maxValue;i++){
            list.add(i);
        }
        return list;
    }

    @Override
    public int roll() {
        return (int) (Math.random() * (maxValue - minValue + 1) + minValue);
    }

    @Override
    public String toString() {
        return "D"+super.diceValue;
    }
}

