package org.co2dice.mirai.bean.dice.single;

import java.util.ArrayList;
import java.util.List;

public abstract class Dice {
    protected int diceValue;

    public Dice(int diceNum) {
        this.diceValue = diceNum;
    }


    public int getDiceValue(){
        return diceValue;
    }
    public void setDiceValue(int diceValue){
        this.diceValue = diceValue;
    }
    public int getDiceMax(){
        return diceValue;
    }
    public void setDiceMax(int diceValue){}
    public int getDiceMin(){return 1;}
    public void setDiceMin(int minValue){}
    public int getDiceTime(){
        return 0;
    }
    public void setDiceTime(int times){
    }
    public abstract List<Integer> getDiceNumArray();
    public int roll(){
        return (int) (Math.random() * diceValue + 1);
    }

    @Override
    public String toString() {
        return "D"+diceValue;
    }
}
