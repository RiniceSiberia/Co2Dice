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
        //获取属性修正值
        TokenPool pond = c.getTokens();
        //获取角色的token池
        List<TokenFuller> tfs = new ArrayList<>();
        for (Token t : tokens){
            //遍历本骰中记载的Token类型，如果角色的token池中有这种类型的token，则将其加入到tfs中
            TokenFuller tf = pond.getPointFuller(t);
            if (tf != null){
                tfs.add(tf);
            }
        }
        if (tfs.size() > 0 && tfs.size() == tokens.size()){
            //如果tfs中有token，且tfs中的token种类与本骰中记载的token种类相同，则返回修正值
            return fixFunc.apply(tfs);
        }
        //否则返回0
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
