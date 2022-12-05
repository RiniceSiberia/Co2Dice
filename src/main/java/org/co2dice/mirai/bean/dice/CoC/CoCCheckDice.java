package org.co2dice.mirai.bean.dice.CoC;

import org.co2dice.mirai.bean.dice.NormalDice;

public class CoCCheckDice extends NormalDice {
    public CoCCheckDice() {
        super(100);
    }
    @Override
    public void setDiceMin(int diceValue) {
        //禁止设置，这玩意定死了100
    }
    @Override
    public void setDiceMax(int diceValue) {
        //禁止设置，这玩意定死了100
    }

    @Override
    public void setDiceValue(int diceValue) {
        //禁止设置，这玩意定死了100
    }
}
