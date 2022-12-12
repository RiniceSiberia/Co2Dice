package org.co2dice.mirai.bean.dice;

import org.co2dice.mirai.bean.cards.character.CharacterCard;
import org.co2dice.mirai.bean.tokens.Token;
import org.co2dice.mirai.bean.tokens.TokenFuller;
import org.co2dice.mirai.bean.tokens.TokenPool;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author DUELIST
 */
public class AttributeFixDice {
    private final List<Token> tokens;
    //属性种类
    private final Function<List<TokenFuller>,DiceList> fixFunc;
    //获取属性修正值的函数

    public AttributeFixDice(List<Token> tokens, Function<List<TokenFuller>, DiceList> fixFunc) {
        this.tokens = tokens;
        this.fixFunc = fixFunc;
    }


    public AttributeFixDice(List<Token> tokens){
        this.tokens = tokens;
        this.fixFunc = (p) -> new DiceList(new ConstantDice((p.stream()
                .mapToInt(TokenFuller::getPoints).sum())/(p.size())));
        //默认的修正值为指定token的平均值
    }

    public DiceList getDiceList(CharacterCard c){
        TokenPool pond = c.getTokens();
        List<TokenFuller> tfs = new ArrayList<>();
        for (Token t : tokens){
            TokenFuller tf = pond.getPointFuller(t);
            if (tf != null){
                tfs.add(tf);
            }
        }
        if (tfs.size() > 0 && tfs.size() == tokens.size()){
            return fixFunc.apply(tfs);
        }
        return new DiceList(new NormalDice(0));
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public Function<List<TokenFuller>, DiceList> getFixFunc() {
        return fixFunc;
    }

    public DiceResult roll(CharacterCard c){
        return getDiceList(c).roll();
    }
}
