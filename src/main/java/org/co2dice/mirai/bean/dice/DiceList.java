package org.co2dice.mirai.bean.dice;

import org.co2dice.mirai.bean.dice.CoC.CoCReRollDice;

import java.util.*;
import java.util.stream.Collectors;

public class DiceList {
    private final List<Dice> diceList;

    public DiceList(Dice... diceList){
        this.diceList = Arrays.asList(diceList);
    }

    public DiceList(List<Dice> diceList) {
        this.diceList = diceList;
    }

    public int roll(){
        int temp;
        int bpNum = diceList.stream().filter(d -> d instanceof CoCReRollDice).mapToInt(Dice::getDiceTime).sum();
        //惩罚骰子和奖励骰子的抵消
        int rollNum = diceList.stream().mapToInt(Dice::roll).sum();
        while (bpNum != 0){
            temp = diceList.stream().mapToInt(Dice::roll).sum();
            if (bpNum > 0){
                bpNum--;
                rollNum = Math.max(rollNum,temp);
            }else {
                bpNum++;
                rollNum = Math.min(rollNum,temp);
            }
        }
        return rollNum;
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

}
