package org.co2dice.mirai.bean.game.decorator.env;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import org.co2dice.mirai.bean.cards.CardsInstance;
import org.co2dice.mirai.bean.cards.api.EffectAPI;
import org.co2dice.mirai.bean.cards.character.CharacterCard;
import org.co2dice.mirai.bean.cards.item.ItemCard;
import org.co2dice.mirai.bean.cards.skill.SkillCard;
import org.co2dice.mirai.bean.game.Scene;
import org.co2dice.mirai.bean.tokens.Token;

import java.util.List;

public class AttributeCostFuncType {
    public static final AttributeCostFuncType COST = new AttributeCostFuncType((e,index) -> {
        if (index != null){
            if (e instanceof SkillCard s){
                return s.getEffects().get(index)::cost;
            }else if (e instanceof ItemCard i){
                return i.getEffects().get(index)::cost;
            }
        }
        return (scene,cards,character,effectAPI) -> List.of();
    });

    private final Function2<CardsInstance,Integer, Function4<Scene, CardsInstance, CharacterCard, EffectAPI<Scene, CardsInstance, CharacterCard>,List<Token>>> getter;

    private AttributeCostFuncType(Function2<CardsInstance,Integer, Function4<Scene, CardsInstance, CharacterCard, EffectAPI<Scene, CardsInstance, CharacterCard>,List<Token>>> getter) {
        this.getter = getter;
    }

    public GetCostAttributeValueInstance getFunc(CardsInstance target, Integer index) {
        return new GetCostAttributeValueInstance(getter.invoke(target,index));
    }
}
