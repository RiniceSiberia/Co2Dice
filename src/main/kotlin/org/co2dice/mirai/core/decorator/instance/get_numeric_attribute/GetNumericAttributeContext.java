package org.co2dice.mirai.core.decorator.instance.get_numeric_attribute;

import org.co2dice.mirai.core.bean.card.instance.CardInstance;
import org.co2dice.mirai.core.decorator.api.DecoratorContext;
import org.co2dice.mirai.core.decorator.env.AttributeNumericType;

public record GetNumericAttributeContext(AttributeNumericType type, CardInstance target)
		implements DecoratorContext<GetNumericAttributeContext> {
	public GetNumericAttributeContext withStat(AttributeNumericType type) {
		return new GetNumericAttributeContext(type, target);
	}
}
