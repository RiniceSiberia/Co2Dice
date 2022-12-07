package org.co2dice.mirai.bean.dice;

import org.co2dice.mirai.bean.tokens.characterToken.CharacterToken;
import org.co2dice.mirai.bean.tokens.TokenFuller;

import java.util.function.Function;

public class AttributeFixDice {
    private final CharacterToken point;
    private final Function<TokenFuller,DiceList> fixFunc;

    public AttributeFixDice(CharacterToken point, Function<TokenFuller, DiceList> fixFunc) {
        this.point = point;
        this.fixFunc = fixFunc;
    }

    public AttributeFixDice(CharacterToken point){
        this.point = point;
        this.fixFunc = (p) -> new DiceList(new ConstantDice(p.getValue()/2));
    }
}
