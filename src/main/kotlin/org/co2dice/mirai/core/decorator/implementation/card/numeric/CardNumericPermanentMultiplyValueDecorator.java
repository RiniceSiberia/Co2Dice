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
public final class CardNumericPermanentMultiplyValueDecorator extends CardPermanentDecorator<
		GetCardNumericDecorator, GetCardNumericContext, GetCardNumericValueInstance
		> implements GetCardNumericDecorator {

	private final CardNumericType type;
	private final int value;

	public CardNumericPermanentMultiplyValueDecorator(CardNumericType type, int value) {
		this.type = type;
		this.value = value;
	}

	@Override
	public GetCardNumericValueInstance apply(
			DecoratorHandler<
					GetCardNumericDecorator,
					GetCardNumericContext,
					GetCardNumericValueInstance> next,
			GetCardNumericContext context
	) {
		GetCardNumericValueInstance val = next.apply(context);
		if (context.type() == type) {
			return val.multiply(value);
		}
		return val;
	}

	public CardNumericType getType() {
		return type;
	}

	public int getValue() {
		return value;
	}
}
