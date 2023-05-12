package org.co2dice.mirai.core.decorator.instance.card.string;

import org.co2dice.mirai.core.decorator.api.DefaultDecorator;

/**
 * @author lcy0x1
 */
public final class DefaultGetCardStringDecorator extends DefaultDecorator<GetCardStringDecorator,
		GetCardStringContext,
		GetCardStringValueInstance>
		implements GetCardStringDecorator {

	@Override
	public GetCardStringValueInstance apply(GetCardStringContext context) {
		return context.type().getValue(context.target());
	}

}
