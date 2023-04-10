package org.co2dice.mirai.bean.dice.single.CoC;

import org.co2dice.mirai.bean.dice.single.AbstractDice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DUELIST
 */
public class CoCReRollDice extends AbstractDice {
    //该骰子不会计入实际计算骰面，只会根据time对最终结果进行调整
    private int times;
    public CoCReRollDice(int times) {
        super(0);
        this.times = times;
    }
    @Override
    public int roll() {
        return 0;
    }
    @Override
    public int getDiceTime(){
        return this.times;
    }
    @Override
    public void setDiceTime(int times){
        this.times = times;
    }

    @Override
    public List<Integer> getDiceNumArray() {
        return new ArrayList<>();
    }
}
