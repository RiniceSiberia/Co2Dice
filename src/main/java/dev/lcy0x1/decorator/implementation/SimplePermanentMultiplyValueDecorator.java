package dev.lcy0x1.decorator.implementation;

import dev.lcy0x1.decorator.env.AttributeType;
import dev.lcy0x1.decorator.handler.DecoratorHandler;
import dev.lcy0x1.decorator.instance.get_numeric_attribute.GetNumericAttributeContext;
import dev.lcy0x1.decorator.instance.get_numeric_attribute.GetNumericAttributeDecorator;
import dev.lcy0x1.decorator.instance.get_numeric_attribute.GetNumericAttributeValueInstance;

public final class SimplePermanentMultiplyValueDecorator extends SimplePermanentDecorator implements GetNumericAttributeDecorator {

	private final AttributeType type;
	private final double value;

	public SimplePermanentMultiplyValueDecorator(AttributeType type, double value) {
		this.type = type;
		this.value = value;
	}

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
		if (context.type() == type) {
			return val.multiply(value);
		}
		return val;
	}

}
