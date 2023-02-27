package org.co2dice.mirai.decorator.instance.get_numeric_attribute;

import org.co2dice.mirai.bean.card.instance.CardInstance;
import org.co2dice.mirai.decorator.api.DecoratorContext;
import org.co2dice.mirai.decorator.env.AttributeNumericType;

public record GetNumericAttributeContext(AttributeNumericType type, CardInstance target)
		implements DecoratorContext<GetNumericAttributeContext> {
	public GetNumericAttributeContext withStat(AttributeNumericType type) {
		return new GetNumericAttributeContext(type, target);
	}
}
