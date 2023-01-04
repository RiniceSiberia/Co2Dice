package dev.lcy0x1.decorator.instance.get_numeric_attribute;

import dev.lcy0x1.decorator.api.DecoratorValueInstance;

public record GetNumericAttributeValueInstance(Integer value)
		implements DecoratorValueInstance<GetNumericAttributeValueInstance> {

	public GetNumericAttributeValueInstance add(int value) {
		return new GetNumericAttributeValueInstance(this.value + value);
	}

	public GetNumericAttributeValueInstance multiply(double value) {
		return new GetNumericAttributeValueInstance((int) Math.ceil(this.value * value));
	}

}
