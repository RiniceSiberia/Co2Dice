package org.co2dice.mirai.bean.dice;

import org.co2dice.mirai.bean.chessman.instance.ChessmanInstance;
import org.co2dice.mirai.bean.counter.Counter;
import org.co2dice.mirai.bean.counter.CounterFuller;
import org.co2dice.mirai.bean.counter.CounterPool;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author DUELIST
 */
public class AttributeFixDice {
    private final List<Counter> counters;
    //属性种类
    private final Function<List<CounterFuller>,DiceList> fixFunc;
    //获取属性修正值的函数

    public AttributeFixDice(List<Counter> counters, Function<List<CounterFuller>, DiceList> fixFunc) {
        this.counters = counters;
        this.fixFunc = fixFunc;
    }


    public AttributeFixDice(List<Counter> counters){
        this.counters = counters;
        this.fixFunc = (p) -> new DiceList(new ConstantDice((p.stream()
                .mapToInt(CounterFuller::getPoints).sum())/(p.size())));
        //默认的修正值为指定token的平均值
    }

    public DiceList getDiceList(ChessmanInstance c){
        //获取属性修正值
        CounterPool pond = c.getCounterPool();
        //
        List<CounterFuller> tfs = new ArrayList<>();
        for (Counter t : counters){
            CounterFuller tf = pond.getPointFuller(t);
            if (tf != null){
                tfs.add(tf);
            }
        }
        if (tfs.size() > 0 && tfs.size() == counters.size()){
            return fixFunc.apply(tfs);
        }
        return new DiceList(new NormalDice(0));
    }

    public List<Counter> getTokens() {
        return counters;
    }

    public Function<List<CounterFuller>, DiceList> getFixFunc() {
        return fixFunc;
    }

    public DiceResult roll(ChessmanInstance c){
        return getDiceList(c).roll();
    }
}
