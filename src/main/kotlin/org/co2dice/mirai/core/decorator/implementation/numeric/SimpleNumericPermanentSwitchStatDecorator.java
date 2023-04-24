package org.co2dice.mirai.core.decorator.implementation.numeric;

import org.co2dice.mirai.core.decorator.api.DecoratorHandler;
import org.co2dice.mirai.core.decorator.env.AttributeNumericType;
import org.co2dice.mirai.core.decorator.instance.get_numeric_attribute.GetNumericAttributeContext;
import org.co2dice.mirai.core.decorator.instance.get_numeric_attribute.GetNumericAttributeDecorator;
import org.co2dice.mirai.core.decorator.instance.get_numeric_attribute.GetNumericAttributeValueInstance;

public final class SimpleNumericPermanentSwitchStatDecorator extends SimpleNumericPermanentDecorator<
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
		if (context.type() == AttributeNumericType.CHAOS) {
			return next.apply(context.withStat(AttributeNumericType.ORDER));
		}
		if (context.type() == AttributeNumericType.ORDER) {
			return next.apply(context.withStat(AttributeNumericType.CHAOS));
		}
		return val;
	}

}
