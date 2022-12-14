package org.co2dice.mirai.bean.dice;

import java.util.Collections;
import java.util.List;

public class ConstantDice extends Dice{
    public ConstantDice(int diceNum) {
        super(diceNum);
    }

    @Override
    public List<Integer> getDiceNumArray() {
        return Collections.singletonList(diceValue);
    }

    @Override
    public int roll(){
        return diceValue;
    }

    @Override
    public String toString() {
        return String.valueOf(diceValue);
    }
}
