package org.co2dice.mirai.core.decorator.instance.card.numeric;

import org.co2dice.mirai.core.decorator.api.DecoratorValueInstance;

public record GetCardNumericValueInstance(Integer value)
		implements DecoratorValueInstance<GetCardNumericValueInstance> {

	public GetCardNumericValueInstance add(int value) {
		return new GetCardNumericValueInstance(this.value + value);
	}

	public GetCardNumericValueInstance multiply(int value) {
		return new GetCardNumericValueInstance((int) Math.ceil(this.value * value));
	}

	public GetCardNumericValueInstance divide(int value) {
		return new GetCardNumericValueInstance(this.value / value);
	}

}
