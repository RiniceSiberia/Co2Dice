package org.co2dice.mirai.bean.game.decorator.env;

import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import org.co2dice.mirai.bean.cards.Cards;
import org.co2dice.mirai.bean.cards.api.CAO;
import org.co2dice.mirai.bean.cards.character.CharacterCard;
import org.co2dice.mirai.bean.cards.item.ItemCard;
import org.co2dice.mirai.bean.cards.skill.SkillCard;
import org.co2dice.mirai.bean.game.Scene;
import org.co2dice.mirai.bean.game.decorator.instance.get_effect_func_attribute.GetEffectFuncAttributeValueInstance;

import java.util.function.Function;

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
           }else if (e instanceof ItemCard i){
               return i.getEffects().get(index).getFunction();
           }
       }
       return (scene,cards,character) -> {
           return false;
       };
   });

   private final Function2<Cards,Integer, Function3<Scene,Cards, CharacterCard,Boolean>> getter;

   private AttributeEffectFuncType(Function2<Cards,Integer, Function3<Scene,Cards, CharacterCard,Boolean>> getter) {
       this.getter = getter;
   }

   public GetEffectFuncAttributeValueInstance getValue(Cards target,Integer index) {
       return new GetEffectFuncAttributeValueInstance(getter.invoke(target,index));
   }
}
