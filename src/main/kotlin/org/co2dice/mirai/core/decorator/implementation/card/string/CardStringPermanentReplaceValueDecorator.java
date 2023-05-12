package org.co2dice.mirai.core.decorator.implementation.card.string;

import org.co2dice.mirai.core.decorator.api.DecoratorHandler;
import org.co2dice.mirai.core.decorator.env.card.string.CardStringType;
import org.co2dice.mirai.core.decorator.implementation.card.CardPermanentDecorator;
import org.co2dice.mirai.core.decorator.instance.card.string.GetCardStringContext;
import org.co2dice.mirai.core.decorator.instance.card.string.GetCardStringDecorator;
import org.co2dice.mirai.core.decorator.instance.card.string.GetCardStringValueInstance;

/**
 * @author lcy0x1
 */
public final class CardStringPermanentReplaceValueDecorator extends CardPermanentDecorator<
		GetCardStringDecorator, GetCardStringContext, GetCardStringValueInstance
		> implements GetCardStringDecorator {

	private final CardStringType type;
	private final String value;

	public CardStringPermanentReplaceValueDecorator(CardStringType type, String value) {
		this.type = type;
		this.value = value;
	}

	@Override
	public GetCardStringValueInstance apply(
			DecoratorHandler<
					GetCardStringDecorator,
					GetCardStringContext,
					GetCardStringValueInstance> next,
			GetCardStringContext context
	) {
		GetCardStringValueInstance val = next.apply(context);
		if (context.type() == type) {
			return val.replace(value);
		}
		return val;
	}

	public CardStringType getType() {
		return type;
	}

	public String getValue() {
		return value;
	}
}
