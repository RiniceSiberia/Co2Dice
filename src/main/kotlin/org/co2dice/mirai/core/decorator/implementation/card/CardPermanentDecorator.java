package org.co2dice.mirai.core.decorator.implementation.card;

import org.co2dice.mirai.core.decorator.api.Decorator;
import org.co2dice.mirai.core.decorator.api.DecoratorContext;
import org.co2dice.mirai.core.decorator.api.DecoratorModifier;
import org.co2dice.mirai.core.decorator.api.DecoratorValueInstance;

/**
 * @author lcy0x1
 */
public abstract class CardPermanentDecorator<
		D extends Decorator<D, C, V>,
		C extends Record & DecoratorContext<C>,
		V extends Record & DecoratorValueInstance<V>
		> extends DecoratorModifier<D, C, V> {

	@Override
	public boolean isValid() {
		return true;
	}

}
