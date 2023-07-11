package org.co2dice.mirai.core.decorator.instance.card.string;

import org.co2dice.mirai.core.bean.card.instance.CardInstance;
import org.co2dice.mirai.core.decorator.api.DecoratorContext;
import org.co2dice.mirai.core.decorator.env.card.string.CardStringType;

public record GetCardStringContext(CardStringType type, CardInstance target)
		implements DecoratorContext<GetCardStringContext> {
	public GetCardStringContext withStat(CardStringType type) {
		return new GetCardStringContext(type, target);
	}
}
