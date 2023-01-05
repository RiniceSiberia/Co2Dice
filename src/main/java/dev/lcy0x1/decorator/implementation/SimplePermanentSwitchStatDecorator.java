package dev.lcy0x1.decorator.implementation;

import dev.lcy0x1.decorator.env.AttributeType;
import dev.lcy0x1.decorator.api.DecoratorHandler;
import dev.lcy0x1.decorator.instance.get_numeric_attribute.GetNumericAttributeContext;
import dev.lcy0x1.decorator.instance.get_numeric_attribute.GetNumericAttributeDecorator;
import dev.lcy0x1.decorator.instance.get_numeric_attribute.GetNumericAttributeValueInstance;

public final class SimplePermanentSwitchStatDecorator extends SimplePermanentDecorator<
		GetNumericAttributeDecorator, GetNumericAttributeContext, GetNumericAttributeValueInstance
		> implements GetNumericAttributeDecorator {

	@Override
	public GetNumericAttributeValueInstance apply(
			DecoratorHandler<
					GetNumericAttributeDecorator,
					GetNumericAttributeContext,
					GetNumericAttributeValueInstance
					> next,
			GetNumericAttributeContext context
	) {
		GetNumericAttributeValueInstance val = next.apply(context);
		if (context.type() == AttributeType.ATK) {
			return next.apply(context.withStat(AttributeType.DEF));
		}
		if (context.type() == AttributeType.DEF) {
			return next.apply(context.withStat(AttributeType.ATK));
		}
		return val;
	}

}
