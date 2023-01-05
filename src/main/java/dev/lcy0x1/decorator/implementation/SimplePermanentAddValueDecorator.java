package dev.lcy0x1.decorator.implementation;

import dev.lcy0x1.decorator.api.DecoratorHandler;
import dev.lcy0x1.decorator.env.AttributeType;
import dev.lcy0x1.decorator.instance.get_numeric_attribute.GetNumericAttributeContext;
import dev.lcy0x1.decorator.instance.get_numeric_attribute.GetNumericAttributeDecorator;
import dev.lcy0x1.decorator.instance.get_numeric_attribute.GetNumericAttributeValueInstance;

public final class SimplePermanentAddValueDecorator extends SimplePermanentDecorator<
		GetNumericAttributeDecorator, GetNumericAttributeContext, GetNumericAttributeValueInstance
		> implements GetNumericAttributeDecorator {

	private final AttributeType type;
	private final int value;

	public SimplePermanentAddValueDecorator(AttributeType type, int value) {
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
			return val.add(value);
		}
		return val;
	}

	public int value() {
		return value;
	}

}
