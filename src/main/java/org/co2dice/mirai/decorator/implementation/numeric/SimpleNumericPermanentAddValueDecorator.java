package org.co2dice.mirai.decorator.implementation.numeric;

import org.co2dice.mirai.decorator.api.DecoratorHandler;
import org.co2dice.mirai.decorator.env.AttributeNumericType;
import org.co2dice.mirai.decorator.instance.get_numeric_attribute.GetNumericAttributeContext;
import org.co2dice.mirai.decorator.instance.get_numeric_attribute.GetNumericAttributeDecorator;
import org.co2dice.mirai.decorator.instance.get_numeric_attribute.GetNumericAttributeValueInstance;

public final class SimpleNumericPermanentAddValueDecorator extends SimpleNumericPermanentDecorator<
        GetNumericAttributeDecorator, GetNumericAttributeContext, GetNumericAttributeValueInstance
		> implements GetNumericAttributeDecorator {

	private final AttributeNumericType type;
	private final int value;

	public SimpleNumericPermanentAddValueDecorator(AttributeNumericType type, int value) {
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
