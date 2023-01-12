package org.co2dice.mirai.bean.game.decorator.implementation;

import org.co2dice.mirai.bean.game.decorator.api.DecoratorHandler;
import org.co2dice.mirai.bean.game.decorator.env.AttributeNumericType;
import org.co2dice.mirai.bean.game.decorator.instance.get_numeric_attribute.GetNumericAttributeContext;
import org.co2dice.mirai.bean.game.decorator.instance.get_numeric_attribute.GetNumericAttributeDecorator;
import org.co2dice.mirai.bean.game.decorator.instance.get_numeric_attribute.GetNumericAttributeValueInstance;

public final class SimplePermanentMultiplyValueDecorator extends SimplePermanentDecorator<
        GetNumericAttributeDecorator, GetNumericAttributeContext, GetNumericAttributeValueInstance
		> implements GetNumericAttributeDecorator {

	private final AttributeNumericType type;
	private final int value;

	public SimplePermanentMultiplyValueDecorator(AttributeNumericType type, int value) {
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
