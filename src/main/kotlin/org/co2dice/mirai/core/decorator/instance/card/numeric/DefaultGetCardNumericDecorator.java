package org.co2dice.mirai.core.decorator.instance.card.numeric;

import org.co2dice.mirai.core.decorator.api.DefaultDecorator;
import org.co2dice.mirai.core.decorator.instance.card.numeric.GetCardNumericContext;
import org.co2dice.mirai.core.decorator.instance.card.numeric.GetCardNumericDecorator;
import org.co2dice.mirai.core.decorator.instance.card.numeric.GetCardNumericValueInstance;

/**
 * @author lcy0x1
 */
public final class DefaultGetCardNumericDecorator
		extends DefaultDecorator<GetCardNumericDecorator,
		GetCardNumericContext,
		GetCardNumericValueInstance>
		implements GetCardNumericDecorator {

	@Override
	public GetCardNumericValueInstance apply(GetCardNumericContext context) {
		return context.type().getValue(context.target());
	}

}
