package org.co2dice.mirai.bean.dice;

import org.co2dice.mirai.bean.cards.character.CharacterCard;
import org.co2dice.mirai.bean.dice.CoC.CoCReRollDice;

import java.util.*;
import java.util.stream.Collectors;
/**
  * @author 韩左券
  * @date 2022/12/8 15:16
  * @input
  * @return_value
  * @message 骰子组，可以存放多个骰子，并使用封装好的批处理方法。注：如果要使用可随意修改参数的骰子，应该使用mutableDiceList
  * @log /
  */
public class DiceList {
    private final List<Dice> diceList;

    public DiceList(Dice... diceList){
        this.diceList = Arrays.asList(diceList);
    }

    @SafeVarargs
    public DiceList(List<Dice>... diceList) {
        List<Dice> temp = new ArrayList<>();
        for (List<Dice> dices : diceList) {
            temp.addAll(dices);
        }
        this.diceList = temp;
    }

    public DiceResult roll(){
        DiceResult temp;
        int bpNum = diceList.stream().filter(d -> d instanceof CoCReRollDice).mapToInt(Dice::getDiceTime).sum();
        //惩罚骰子和奖励骰子的抵消
        DiceResult result = new DiceResult(this);
        while (bpNum != 0){
            temp = new DiceResult(this);
            if (bpNum > 0){
                bpNum--;
                result = result.getResult() > temp.getResult() ? result : temp;
            }else {
                bpNum++;
                result = result.getResult() < temp.getResult() ? result : temp;
            }
        }
        return result;
    }

    public Map<Integer,Double> getExpected(CharacterCard c){
        Map<Integer,Integer> map = Expect.getExcept(this);
        Map<Integer,Double> result = new HashMap<>(map.size());
        int sum = map.values().stream().mapToInt(Integer::intValue).sum();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            result.put(entry.getKey(),  ((double)entry.getValue()/(double)sum));
        }
        return result;
    }

    public List<Dice> getDiceList() {
        return diceList;
    }

    public Integer getMax(){
        return diceList.stream().mapToInt(Dice::getDiceMax).sum();
    }
    public Integer getMin(){
        return diceList.stream().mapToInt(Dice::getDiceMin).sum();
    }
    public Integer getDiceTime(){
        return diceList.stream().mapToInt(Dice::getDiceTime).sum();
    }
    public List<List<Integer>> getDiceNumArray(){
        return diceList.stream().map(Dice::getDiceNumArray).collect(Collectors.toList());
    }

    @Override
    public String toString(){
        //将dice根据string出来的结果进行分割，相同的骰子合并，在前面加个骰子数量
        String normal = diceList.stream().filter(d -> !(d instanceof ConstantDice)).map(Dice::toString)
                . collect(Collectors.groupingBy(e -> e, Collectors.counting()))
                .entrySet().stream().map(e -> e.getValue() + e.getKey())
                .collect(Collectors.joining(" + "));
        String c = diceList.stream().anyMatch(d -> d instanceof ConstantDice) ?
                String.valueOf(diceList.stream().filter(d -> d instanceof ConstantDice).mapToInt(Dice::getDiceValue).sum()) : "";
        if (diceList.stream().filter(d -> d instanceof ConstantDice).mapToInt(Dice::getDiceValue).sum() > 0){
            c = "+" + c;
        }
        return normal +c;
    }

}
