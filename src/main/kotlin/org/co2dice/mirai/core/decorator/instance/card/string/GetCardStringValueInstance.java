package org.co2dice.mirai.core.decorator.instance.card.string;

import org.co2dice.mirai.core.decorator.api.DecoratorValueInstance;

public record GetCardStringValueInstance(String value)
		implements DecoratorValueInstance<GetCardStringValueInstance> {

	public GetCardStringValueInstance add(String value) {
		return new GetCardStringValueInstance(this.value + "|" + value);
	}

	public GetCardStringValueInstance replace(String value) {
		return new GetCardStringValueInstance(value);
	}

}
