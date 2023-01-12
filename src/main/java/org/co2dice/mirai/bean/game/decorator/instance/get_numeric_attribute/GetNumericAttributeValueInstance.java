package org.co2dice.mirai.bean.game.decorator.instance.get_numeric_attribute;

import org.co2dice.mirai.bean.game.decorator.api.DecoratorValueInstance;

public record GetNumericAttributeValueInstance(Integer value)
		implements DecoratorValueInstance<GetNumericAttributeValueInstance> {

	public GetNumericAttributeValueInstance add(int value) {
		return new GetNumericAttributeValueInstance(this.value + value);
	}

	public GetNumericAttributeValueInstance multiply(int value) {
		return new GetNumericAttributeValueInstance((int) Math.ceil(this.value * value));
	}

	public GetNumericAttributeValueInstance division(int value) {
		return new GetNumericAttributeValueInstance(this.value / value);
	}

}
