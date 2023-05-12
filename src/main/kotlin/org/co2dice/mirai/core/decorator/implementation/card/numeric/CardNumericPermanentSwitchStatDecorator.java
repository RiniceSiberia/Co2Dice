package org.co2dice.mirai.core.decorator.implementation.card.numeric;

import org.co2dice.mirai.core.decorator.api.DecoratorHandler;
import org.co2dice.mirai.core.decorator.env.card.numeric.CardNumericType;
import org.co2dice.mirai.core.decorator.implementation.card.CardPermanentDecorator;
import org.co2dice.mirai.core.decorator.instance.card.numeric.GetCardNumericContext;
import org.co2dice.mirai.core.decorator.instance.card.numeric.GetCardNumericDecorator;
import org.co2dice.mirai.core.decorator.instance.card.numeric.GetCardNumericValueInstance;

/**
 * @author lcy0x1
 */
public final class CardNumericPermanentSwitchStatDecorator extends CardPermanentDecorator<
		GetCardNumericDecorator, GetCardNumericContext, GetCardNumericValueInstance
		> implements GetCardNumericDecorator {

	private final CardNumericType from;
	private final CardNumericType to;

	public CardNumericPermanentSwitchStatDecorator(CardNumericType from, CardNumericType to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public GetCardNumericValueInstance apply(
			DecoratorHandler<
					GetCardNumericDecorator,
					GetCardNumericContext,
					GetCardNumericValueInstance> next,
			GetCardNumericContext context) {
		GetCardNumericValueInstance val = next.apply(context);
		if (context.type() == from) {
			return next.apply(context.withStat(to));
		}
		return val;
	}

	public CardNumericType getFrom() {
		return from;
	}

	public CardNumericType getTo() {
		return to;
	}
}
