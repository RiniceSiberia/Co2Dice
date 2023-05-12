package org.co2dice.mirai.core.decorator.instance.card.numeric;

import org.co2dice.mirai.core.bean.card.instance.CardInstance;
import org.co2dice.mirai.core.decorator.api.DecoratorContext;
import org.co2dice.mirai.core.decorator.env.card.numeric.CardNumericType;

public record GetCardNumericContext(CardNumericType type, CardInstance<?> target)
		implements DecoratorContext<GetCardNumericContext> {
	public GetCardNumericContext withStat(CardNumericType type) {
		return new GetCardNumericContext(type, target);
	}
}
