package org.co2dice.mirai.bean.game.decorator.env;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.co2dice.mirai.bean.game.instance.card.CardInstance;
import org.co2dice.mirai.bean.game.instance.card.Situation;
import org.co2dice.mirai.bean.game.instance.card.item.ItemCardInstance;
import org.co2dice.mirai.bean.game.instance.card.skill.SkillCard;
import org.co2dice.mirai.bean.game.decorator.instance.get_effect_func_attribute.GetEffectFuncAttributeValueInstance;

/**
* 属性的间接实体，记录了获取属性的方法，此处是返回效果类型的属性
*/
public final class AttributeEffectFuncType {

//	@Deprecated
//	public static final AttributeNumericType ATK = new AttributeNumericType(e -> e.atk);
//	@Deprecated
//	public static final AttributeNumericType DEF = new AttributeNumericType(e -> e.def);
//

   public static final AttributeEffectFuncType EFFECT_FUNC = new AttributeEffectFuncType((e,index) -> {
       if (index != null){
           if (e instanceof SkillCard s){
               return s.getEffects().get(index).getFunction();
           }else if (e instanceof ItemCardInstance i){
               return i.getEffects().get(index).getFunction();
           }
       }
       return (situation) -> false;
   });

   private final Function2<CardInstance,Integer, Function1<Situation,Boolean>> getter;

   private AttributeEffectFuncType(Function2<CardInstance,Integer, Function1<Situation,Boolean>> getter) {
       this.getter = getter;
   }

   public GetEffectFuncAttributeValueInstance getFunc(CardInstance target, Integer index) {
       return new GetEffectFuncAttributeValueInstance(getter.invoke(target,index));
   }
}
