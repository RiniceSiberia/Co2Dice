package org.co2dice.mirai.bean.game.decorator.env;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import org.co2dice.mirai.bean.game.api.EffectAPI;
import org.co2dice.mirai.bean.game.instance.card.CardInstance;
import org.co2dice.mirai.bean.game.prototype.character.Chessman;
import org.co2dice.mirai.bean.game.instance.card.item.ItemCardInstance;
import org.co2dice.mirai.bean.game.instance.card.skill.SkillCardInstance;
import org.co2dice.mirai.bean.game.Scene;
import org.co2dice.mirai.bean.tokens.Token;

import java.util.List;

public class AttributeCostFuncType {
    public static final AttributeCostFuncType COST = new AttributeCostFuncType((e,index) -> {
        if (index != null){
            if (e instanceof SkillCardInstance s){
                return s.getEffects().get(index)::cost;
            }else if (e instanceof ItemCardInstance i){
                return i.getEffects().get(index)::cost;
            }
        }
        return (scene, cards, character, effectAPI) -> List.of();
    });

    private final Function2<CardInstance,Integer, Function4<Scene, CardInstance, Chessman, EffectAPI<Scene, CardInstance, Chessman>,List<Token>>> getter;

    private AttributeCostFuncType(Function2<CardInstance,Integer, Function4<Scene, CardInstance, Chessman, EffectAPI<Scene, CardInstance, Chessman>,List<Token>>> getter) {
        this.getter = getter;
    }

    public GetCostAttributeValueInstance getFunc(CardInstance target, Integer index) {
        return new GetCostAttributeValueInstance(getter.invoke(target,index));
    }
}
