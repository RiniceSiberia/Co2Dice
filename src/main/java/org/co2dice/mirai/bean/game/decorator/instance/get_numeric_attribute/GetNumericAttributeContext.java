package org.co2dice.mirai.bean.game.decorator.instance.get_numeric_attribute;

import org.co2dice.mirai.bean.cards.Cards;
import org.co2dice.mirai.bean.game.decorator.api.DecoratorContext;
import org.co2dice.mirai.bean.game.decorator.env.AttributeNumericType;

public record GetNumericAttributeContext(AttributeNumericType type, Cards target)
		implements DecoratorContext<GetNumericAttributeContext> {
	public GetNumericAttributeContext withStat(AttributeNumericType type) {
		return new GetNumericAttributeContext(type, target);
	}
}
