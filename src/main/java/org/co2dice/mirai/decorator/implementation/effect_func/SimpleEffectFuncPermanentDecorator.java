package org.co2dice.mirai.decorator.implementation.effect_func;

import org.co2dice.mirai.decorator.api.Decorator;
import org.co2dice.mirai.decorator.api.DecoratorContext;
import org.co2dice.mirai.decorator.api.DecoratorModifier;
import org.co2dice.mirai.decorator.api.DecoratorValueInstance;

public abstract class SimpleEffectFuncPermanentDecorator<
		D extends Decorator<D, C, V>,
		C extends Record & DecoratorContext<C>,
		V extends Record & DecoratorValueInstance<V>
		> extends DecoratorModifier<D, C, V> {

	public boolean isValid() {
		return true;
	}

}
