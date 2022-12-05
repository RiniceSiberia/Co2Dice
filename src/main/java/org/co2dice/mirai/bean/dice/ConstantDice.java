package org.co2dice.mirai.bean.dice;

import java.util.List;

public class ConstantDice extends Dice{
    public ConstantDice(int diceNum) {
        super(diceNum);
    }

    @Override
    public List<Integer> getDiceNumArray() {
        return List.of(super.getDiceValue());
    }

    @Override
    public int roll(){
        return diceValue;
    }
}
