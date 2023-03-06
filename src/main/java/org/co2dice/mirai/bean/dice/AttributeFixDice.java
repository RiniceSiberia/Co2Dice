package org.co2dice.mirai.bean.dice;

import com.mojang.datafixers.util.Either;
import kotlin.jvm.functions.Function1;
import org.co2dice.mirai.bean.chessman.attribute.*;
import org.co2dice.mirai.bean.chessman.instance.ChessmanInstance;
import org.co2dice.mirai.bean.counter.Counter;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author DUELIST
 * 这个类的目的是，将抽象的属性修正值转换为具体的骰子修正值，比如将玩家的体质转化为体质数值的骰子
 * 可能存在多个属性的修正值，比如玩家的体质/2+意志/2
 *
 */
public class AttributeFixDice {

    private final Function1<AttributeInstanceTable,Either<DiceList,String>> fixFunc;
    //获取属性修正值的函数

    public AttributeFixDice( Function1<AttributeInstanceTable,Either<DiceList,String>> fixFunc) {
        this.fixFunc = fixFunc;
    }


    public AttributeFixDice(AttributeAPI a) {
        this.fixFunc = (table -> {
            Integer value = table.getValue(a);
            if( value != null ) {
                return Either.left(new DiceList(value));
            }
            return Either.right("属性修正值为空,为空属性:" + a.getNameStr());
        });
        //默认的修正值,传入属性，返回那个属性的数值
        //如果属性不存在会报错，需要在调用的最外圈加一层try catch
    }

    public boolean canUse(AttributeInstanceTable table){
        return fixFunc.invoke(table).left().isPresent();
    }

    @NotNull
    public Either<DiceList,String> getDiceList(ChessmanInstance c){
        //通过玩家获取属性修正值
        var table = c.getAttributeInstanceTable();
        return fixFunc.invoke(table);
    }

    public Either<List<Dice>,String> getListDice(ChessmanInstance c){
        //通过玩家获取属性修正值
        var either = getDiceList(c);
        if(either.left().isPresent()){
            return Either.left(either.left().get().getDiceList());
        }else if (either.right().isPresent()){
            return Either.right(either.right().get());
        }
        return Either.right("未知错误");
    }


    public Set<AttributeAPI> getNeeds() {
        //获取这个修正值需要的属性,使用遍历法
        Set<AttributeAPI> set = new java.util.HashSet<>(Set.of());
        set.addAll(Arrays.stream(EliteAttribute.values()).toList());
        set.addAll(Arrays.stream(MobAttribute.values()).toList());
        //现在这个set里获取了所有类型的属性，无论是不是怪物的

        set.removeIf(a -> {
            //将set里的属性全部转化为AttributeInstance，然后填充入table中
            var table = new AttributeInstanceTable(
                    set.stream().filter(i -> !i.equals(a))
                            .map(i -> new AttributeInstance(i,2,2)).collect(Collectors.toSet())
            );
            return canUse(table);
            //若属性依然可以检定，那么就是需要的属性
        });
        return set;
    }

    public Either<Integer,String> roll(ChessmanInstance c){
        var either = getDiceList(c);
        if(either.left().isPresent()){
            return Either.left(either.left().get().roll().getResult());
        }else if (either.right().isPresent()){
            return Either.right(either.right().get());
        }
        return Either.right("检查属性时发生未知错误");
    }
}
