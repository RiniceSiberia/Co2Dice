package org.co2dice.mirai.bean.dice;

import org.co2dice.mirai.bean.cards.character.CharacterCard;
import org.co2dice.mirai.bean.dice.CoC.CoCReRollDice;

import java.util.List;
import java.util.Map;

public class MutableDiceList extends DiceList{
    private List<Dice> mutable;

    public MutableDiceList(List<Dice> diceList, List<Dice> mutable ) {
        super(diceList);
        this.mutable = mutable;
    }
    public MutableDiceList(DiceList diceList,List<Dice> mutable){
        super(diceList.getDiceList());
        this.mutable = mutable;
    }

    @Override
    public DiceResult roll() {
        DiceList diceList = new DiceList(mutable,super.getDiceList());
        return diceList.roll();
    }

    @Override
    public Map<Integer,Double> getExpected() {
        DiceList diceList = new DiceList(super.getDiceList(),mutable);
        return diceList.getExpected();
    }

    @Override
    public List<Dice> getDiceList() {
        List<Dice> diceList = super.getDiceList();
        diceList.addAll(mutable);
        return diceList;
    }

    public List<Dice> getMutable() {
        return mutable;
    }

    public void setMutable(List<Dice> mutable) {
        this.mutable = mutable;
    }

    public List<Dice> getImmutable(){
        return super.getDiceList();
    }

    @Override
    public String toString() {
        DiceList diceList = new DiceList(mutable);
        return super.toString()+","+diceList.toString();
    }
}
