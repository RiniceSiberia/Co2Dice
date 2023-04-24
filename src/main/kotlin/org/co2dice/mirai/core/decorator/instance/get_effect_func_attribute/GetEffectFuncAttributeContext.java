package org.co2dice.mirai.core.decorator.instance.get_effect_func_attribute;

import org.co2dice.mirai.core.bean.card.instance.CardInstance;
import org.co2dice.mirai.core.decorator.api.DecoratorContext;
import org.co2dice.mirai.core.decorator.env.AttributeEffectFuncType;

public record GetEffectFuncAttributeContext(AttributeEffectFuncType type, CardInstance target, Integer index)
		implements DecoratorContext<GetEffectFuncAttributeContext> {
	public GetEffectFuncAttributeContext withStat(AttributeEffectFuncType type) {
		return new GetEffectFuncAttributeContext(type, target,index);
	}
}
