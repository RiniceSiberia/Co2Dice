package org.co2dice.mirai.bean.dice

enum class UsuallyDices(val dice: Dice,val priority :Int) {
    D1(ConstantDice(1),2),
    COIN(NormalDice(2),1),
    D3(NormalDice(3),6),
    D4(NormalDice(4),7),
    D5(NormalDice(5),5),
    D6(NormalDice(6),11),
    D8(NormalDice(8),8),
    D10(NormalDice(10),9),
    D12(NormalDice(12),10),
    D20(NormalDice(20),12),
    D100(NormalDice(100),13),
    NEGATIVE_1(ConstantDice(-1),4),
    NEGATIVE_2(ConstantDice(-2),3),
    ;

    /**
     * @author 韩左券
     * @date 2022/12/9 10:29
     * @input
     * @return_value
     * @message 将数据从大到小根据权重排序
     * @log /
     */
    fun getListByPriority(): List<UsuallyDices> {
        return values().sortedByDescending { it.priority }
    }

    fun getExceptionList(e:Int):DiceList{
        val dices:MutableDiceList = MutableDiceList(mutableListOf(),mutableListOf())
        //循环遍历将所有的骰子都加入到dices,计算出所有的可能性，再将dices清空，算出最接近期望值的样子
        var exlist = mutableListOf<Double>()
        var f = true
        while (f){
            for (dice in getListByPriority()){
                //获取当前的遍历数
                dices.mutable.add(dice.dice)
                val odds = dices.expected
                //概率
                val min = dices.min
                val max = dices.max
                for (i in 0..max-min){
                    exlist.set(index = dice.priority,element = a@{
                        if(exlist[dice.priority] != null){
                            return@a exlist[dice.priority]
                        }else{
                            return@a 0.0
                        }
                    } + odds[i])
                }
                dices.mutable.remove(dice.dice)
            }
            //现在，exlist中承载了权重从高到低，每个权重下的期望值，需要从中
            var resultList = mutableListOf<Dice>()
            //获取到了所有的期望值
            for (i in 0 until exlist.size){
                if (exlist[i] > e - 0.5 && exlist[i] < e + 0.5){
                    resultList.add(getListByPriority()[i].dice)
                }
            }
            //现在，resultList中承载了所有的期望值在e+-0.5之间的骰子
            //如果resultList为空，则说明没有找到，将概率最接近的骰子加入到dices中
            if (resultList.isEmpty()){
                //将和期望值最接近的骰子丢进去，注意，不是最大的骰子，而是最接近的骰子
                dices.diceList.add(getListByPriority()[exlist.indexOf(exlist.stream().sorted(
                    Comparator.comparingDouble(a,b ->{
                        if(Math.abs(a - e) > Math.abs(b - e)){
                            return@comparingDouble 1
                        }else{
                            return@comparingDouble -1
                        }
                    }
                ).findFirst().get())].dice))
            }else{
                //如果不为空，则获取resultList的第一个元素，丢入dices中，结束循环
                dices.diceList.add(resultList[0])
                f = false
            }
        }


    }

}