package dev.lcy0x1.decorator.instance.get_numeric_attribute;

import dev.lcy0x1.decorator.api.DecoratorContext;
import dev.lcy0x1.decorator.env.AttributeType;
import dev.lcy0x1.decorator.env.Card;

public record GetNumericAttributeContext(AttributeType type, Card target)
		implements DecoratorContext<GetNumericAttributeContext> {

	public GetNumericAttributeContext withStat(AttributeType type) {
		return new GetNumericAttributeContext(type, target);
	}

}
