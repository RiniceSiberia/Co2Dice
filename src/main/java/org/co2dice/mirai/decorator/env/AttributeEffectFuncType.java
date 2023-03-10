package org.co2dice.mirai.decorator.env;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import org.co2dice.mirai.bean.card.instance.CardInstance;
import org.co2dice.mirai.decorator.instance.get_effect_func_attribute.GetEffectFuncAttributeValueInstance;
import org.co2dice.mirai.bean.effect.utils.Situation;
import org.co2dice.mirai.bean.card.instance.item.ItemCardInstance;
import org.co2dice.mirai.bean.card.instance.skill.SkillCardInstance;

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
           if (e instanceof SkillCardInstance s){
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
