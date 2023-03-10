package org.co2dice.mirai.decorator.env;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import org.co2dice.mirai.bean.api.EffectAPI;
import org.co2dice.mirai.bean.card.instance.CardInstance;
import org.co2dice.mirai.bean.chessman.prototype.Chessman;
import org.co2dice.mirai.bean.card.instance.item.ItemCardInstance;
import org.co2dice.mirai.bean.card.instance.skill.SkillCardInstance;
import org.co2dice.mirai.bean.game.Scene;
import org.co2dice.mirai.bean.counter.Counter;

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

    private final Function2<CardInstance,Integer, Function4<Scene, CardInstance, Chessman, EffectAPI<Scene, CardInstance, Chessman>,List<Counter>>> getter;

    private AttributeCostFuncType(Function2<CardInstance,Integer, Function4<Scene, CardInstance, Chessman, EffectAPI<Scene, CardInstance, Chessman>,List<Counter>>> getter) {
        this.getter = getter;
    }

    public GetCostAttributeValueInstance getFunc(CardInstance target, Integer index) {
        return new GetCostAttributeValueInstance(getter.invoke(target,index));
    }
}
