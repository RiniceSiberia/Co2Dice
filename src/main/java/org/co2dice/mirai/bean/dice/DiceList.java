package org.co2dice.mirai.bean.dice;

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

    public List<Double> getExpected(){
        int[][] array= new int[][]{};
        for (int i = 0;i < diceList.size();i++){
            List<Integer> diceNum = diceList.get(i).getDiceNumArray();
            for (int j = 0; j < diceNum.size();j++){
                array[i][j] = diceNum.get(j);
            }
        }
        D d = new D();
        d.getD(array);
        Map<Integer,Integer> map = d.map;
        List<Integer> values = new ArrayList<>();
        //出现次数
        Set<Integer> keySet = map.keySet();
        for (Integer k : keySet){
            values.set(k,map.get(k));
        }
        return values.stream()
                    .map(e -> e.doubleValue() / (double) keySet.size()).collect(Collectors.toList());
    }

    private class D{
        int[][] data = new int[][]{};

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        void dfs(D d,int row, int total) {
            //实体，上一行的坐标，计算和
            if (row == d.data.length) {
                d.map.put(total, d.map.getOrDefault(total, 0) + 1);
                return;
            }

            for (int i = 0; i < d.data[row].length; i++) {
                this.dfs(d,row + 1, total + d.data[row][i]);
            }
        }

        public void getD(int[][] data1) {
            D d =new D();
            d.data = data1;
            try {
                this.dfs(d,0, 0);
                System.out.println(d.map);
            }catch (OutOfMemoryError e){
                System.out.println("内存溢出");
                e.printStackTrace();
            }catch (NullPointerException e){
                System.out.println("空指针异常");
                e.printStackTrace();
            }
        }
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

}
