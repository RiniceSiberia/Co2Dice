package org.co2dice.mirai.bean.dice;

import org.co2dice.mirai.bean.permanents.attribute.AbstractAttributePoint;
import org.co2dice.mirai.bean.permanents.PermanentsFuller;

import java.util.function.Function;

public class AttributeFixDice {
    private final AbstractAttributePoint point;
    private final Function<PermanentsFuller,DiceList> fixFunc;

    public AttributeFixDice(AbstractAttributePoint point, Function<PermanentsFuller, DiceList> fixFunc) {
        this.point = point;
        this.fixFunc = fixFunc;
    }

    public AttributeFixDice(AbstractAttributePoint point){
        this.point = point;
        this.fixFunc = (p) -> new DiceList(new ConstantDice(p.getValue/3));
    }
}
