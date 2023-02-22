package org.co2dice.mirai.bean.dice;

import org.co2dice.mirai.bean.game.instance.chess.ChessInstance;
import org.co2dice.mirai.bean.game.prototype.character.Chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MutableDiceList extends DiceList{
    private List<Dice> mutable;
    //可变的修正值，技能牌丢弃去弃牌堆就将属性加在这里。
    private AttributeFixDice fixDice;
    //属性修正值，玩家的属性加成加在这里，不包含在期望计算中。需要使用新的方法计算期望值

    public MutableDiceList(List<Dice> diceList, List<Dice> mutable,AttributeFixDice fix) {
        super(diceList);
        this.mutable = mutable;
        this.fixDice = fix;
    }


    @Override
    public DiceResult roll() {
        DiceList diceList = new DiceList(super.getDiceList(),mutable);
        return diceList.roll();
    }
    public DiceResult rollContainAttribute(ChessInstance c){
        DiceList diceList = new DiceList(super.getDiceList(),mutable,fixDice.getDiceList(c).getDiceList());
        return diceList.roll();
    }

    @Override
    public Map<Integer,Double> getExpected() {
        DiceList diceList = new DiceList(super.getDiceList(),mutable);
        return diceList.getExpected();
    }

    public Map<Integer,Double> getExpectedContainAttribute(ChessInstance c) {
        DiceList diceList = new DiceList(super.getDiceList(),mutable,fixDice.getDiceList(c).getDiceList());
        return diceList.getExpected();
    }

    @Override
    public List<Dice> getDiceList() {
        List<Dice> d = new ArrayList<>(super.getDiceList());
        d.addAll(getMutable());
        return d;
    }

    public List<Dice> getDiceListContainAttribute(ChessInstance c) {
        List<Dice> d = new ArrayList<>(super.getDiceList());
        d.addAll(getMutable());
        d.addAll(fixDice.getDiceList(c).getDiceList());
        return d;
    }

    public AttributeFixDice getFixDice() {
        return fixDice;
    }

    public void setFixDice(AttributeFixDice fixDice) {
        this.fixDice = fixDice;
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



    public Integer getMutableMin(){
        return mutable.stream().mapToInt(Dice::getDiceMin).sum();
    }
    public Integer getMutableMax(){
        return mutable.stream().mapToInt(Dice::getDiceMax).sum();
    }
    public Integer getImmutableMin(){
        return super.getDiceList().stream().mapToInt(Dice::getDiceMin).sum();
    }
    public Integer getImmutableMax(){
        return super.getDiceList().stream().mapToInt(Dice::getDiceMax).sum();
    }
    @Override
    public Integer getMin() {
        return getMutableMin()+getImmutableMin();
    }

    @Override
    public Integer getMax() {
        return getMutableMax()+getImmutableMax();
    }

    @Override
    public String toString() {
        DiceList diceList = new DiceList(mutable);
        return super.toString()+","+diceList.toString();
    }
}
