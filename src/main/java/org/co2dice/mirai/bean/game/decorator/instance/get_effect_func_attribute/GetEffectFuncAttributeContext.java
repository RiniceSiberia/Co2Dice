package org.co2dice.mirai.bean.game.decorator.instance.get_effect_func_attribute;

import org.co2dice.mirai.bean.cards.Cards;
import org.co2dice.mirai.bean.game.decorator.api.DecoratorContext;
import org.co2dice.mirai.bean.game.decorator.env.AttributeEffectFuncType;

public record GetEffectFuncAttributeContext(AttributeEffectFuncType type, Cards target,Integer index)
		implements DecoratorContext<GetEffectFuncAttributeContext> {
	public GetEffectFuncAttributeContext withStat(AttributeEffectFuncType type) {
		return new GetEffectFuncAttributeContext(type, target,index);
	}
}
