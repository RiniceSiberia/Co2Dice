package org.co2dice.mirai.bean.game.decorator.instance.get_numeric_attribute;

import org.co2dice.mirai.bean.game.decorator.api.DefaultDecorator;

public final class DefaultGetNumericAttributeDecorator
		extends DefaultDecorator<GetNumericAttributeDecorator,
        GetNumericAttributeContext,
        GetNumericAttributeValueInstance>
		implements GetNumericAttributeDecorator {

	@Override
	public GetNumericAttributeValueInstance apply(GetNumericAttributeContext context) {
		return context.type().getValue(context.target());
	}

}
