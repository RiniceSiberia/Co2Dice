package org.co2dice.mirai.bean.dice;

import com.mojang.datafixers.util.Either;
import kotlin.jvm.functions.Function1;
import org.co2dice.mirai.bean.chessman.attribute.AttributeInstanceTable;
import org.co2dice.mirai.bean.chessman.instance.ChessmanInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;

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

    @TestOnly
    public MutableDiceList(List<Dice> diceList, List<Dice> mutable) {
        super(diceList);
        this.mutable = mutable;
        this.fixDice = new AttributeFixDice(table -> Either.right("没有属性修正值"));
    }
    //只在测试时使用


    @Override
    public DiceResult roll() {
        DiceList diceList = new DiceList(super.getDiceList(),mutable);
        return diceList.roll();
    }

    @NotNull
    private Either<List<Dice>,String> getFixDiceEither(ChessmanInstance c){
        if (this.fixDice.getListDice(c).left().isPresent()){
            return Either.left(this.fixDice.getListDice(c).left().get());
        }else {
            if (this.fixDice.getListDice(c).right().isPresent()){
                return Either.right(this.fixDice.getListDice(c).right().get());
            }
            return Either.right("roll属性时发生未知错误");
        }
    }

    public Either<DiceResult,String> rollContainAttribute(ChessmanInstance c){
        if (getFixDiceEither(c).left().isPresent()){
            var diceList = new DiceList(super.getDiceList(),mutable,getFixDiceEither(c).left().get());
            return Either.left(diceList.roll());
        }else if (getFixDiceEither(c).right().isPresent()){
            return Either.right(getFixDiceEither(c).right().get());
        }
        return Either.right("roll属性时发生未知错误");
    }

    @Override
    public Map<Integer,Double> getExpected() {
        DiceList diceList = new DiceList(super.getDiceList(),mutable);
        return diceList.getExpected();
    }

    public Either<Map<Integer,Double>,String> getExpectedContainAttribute(ChessmanInstance c) {
        if (getFixDiceEither(c).left().isPresent()){
            DiceList diceList = new DiceList(super.getDiceList(),mutable,getFixDiceEither(c).left().get());
            return Either.left(diceList.getExpected());
        }else if (getFixDiceEither(c).right().isPresent()){
            return Either.right(getFixDiceEither(c).right().get());
        }
        return Either.right("roll属性时发生未知错误");
    }

    @Override
    public List<Dice> getDiceList() {
        List<Dice> d = new ArrayList<>(super.getDiceList());
        d.addAll(getMutable());
        return d;
    }

    public Either<List<Dice>,String> getDiceListContainAttribute(ChessmanInstance c) {
        if (getFixDiceEither(c).left().isPresent()) {
            List<Dice> d = new ArrayList<>(super.getDiceList());
            d.addAll(getMutable());
            d.addAll(getFixDiceEither(c).left().get());
            return Either.left(d);
        }else if (getFixDiceEither(c).right().isPresent()){
            return Either.right(getFixDiceEither(c).right().get());
        }
        return Either.right("roll属性时发生未知错误");
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
