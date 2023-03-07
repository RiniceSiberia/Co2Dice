package org.co2dice.mirai.bean.dice.utils;

import org.co2dice.mirai.bean.dice.diceList.DiceList;

import java.util.HashMap;
import java.util.Map;

public class Expect {
     int[][] data;
     //骰子的数组，第一个是骰子序号，第二个是骰子所有面的数组
     Map<Integer, Integer> map;

    public Expect(int[][] data, Map<Integer, Integer> map) {
        this.data = data;
        this.map = map;
    }

    void dfs(int row, int total) {
        if (row == data.length) {
            map.put(total, map.getOrDefault(total, 0) + 1);
            return;
        }

        for (int i = 0; i < data[row].length; i++) {
            dfs(row + 1, total + data[row][i]);
        }
    }

    public static Map<Integer,Integer> getExcept(DiceList diceList) {
        Expect e = new Expect(new int[diceList.getDiceListSize()][], new HashMap<>());
        for (int i = 0; i < diceList.getDiceListSize() ; i++){
            e.data[i] = diceList.getDiceByIndex(i).getDiceNumArray().stream().mapToInt(Integer::valueOf).toArray();
        }
        e.dfs(0, 0);
        return e.map;
    }

}
