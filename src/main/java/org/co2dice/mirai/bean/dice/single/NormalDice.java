package org.co2dice.mirai.bean.dice.single;

import java.util.ArrayList;
import java.util.List;

public class NormalDice extends AbstractDice {
    public NormalDice(int diceNum) {
        super(diceNum);
    }

    @Override
    public List<Integer> getDiceNumArray() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= diceValue;i++){
            list.add(i);
        }
        return list;
    }
}
