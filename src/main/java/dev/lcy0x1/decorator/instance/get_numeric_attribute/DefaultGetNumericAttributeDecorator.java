package dev.lcy0x1.decorator.instance.get_numeric_attribute;

import dev.lcy0x1.decorator.api.DefaultDecorator;

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
