package org.co2dice.mirai.core.decorator.env;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import org.co2dice.mirai.core.bean.card.instance.CardInstance;
import org.co2dice.mirai.core.bean.chessman.prototype.Chessman;
import org.co2dice.mirai.core.bean.card.instance.item.ItemCardInstance;
import org.co2dice.mirai.core.bean.card.instance.skill.SkillCardInstance;
import org.co2dice.mirai.core.bean.effect.prototype.Effect;
import org.co2dice.mirai.core.bean.game.Scene;
import org.co2dice.mirai.core.bean.counter.prototype.Counter;

import java.util.List;

public class AttributeCostFuncType {
    public static final AttributeCostFuncType COST = new AttributeCostFuncType((e,index) -> {
        if (index != null){
            if (e instanceof SkillCardInstance s){
                return s.getActiveEffects().get(index)::cost;
            }else if (e instanceof ItemCardInstance i){
                return i.getActiveEffects().get(index)::cost;
            }
        }
        return (scene, cards, character, effectAPI) -> List.of();
    });

    private final Function2<CardInstance,Integer, Function4<Scene, CardInstance, Chessman, Effect<Scene, CardInstance, Chessman>,List<Counter>>> getter;

    private AttributeCostFuncType(Function2<CardInstance,Integer, Function4<Scene, CardInstance, Chessman, Effect<Scene, CardInstance, Chessman>,List<Counter>>> getter) {
        this.getter = getter;
    }

    public GetCostAttributeValueInstance getFunc(CardInstance target, Integer index) {
        return new GetCostAttributeValueInstance(getter.invoke(target,index));
    }
}
