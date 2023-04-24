package org.co2dice.mirai.core.decorator.implementation.effect_func;

import org.co2dice.mirai.core.decorator.api.Decorator;
import org.co2dice.mirai.core.decorator.api.DecoratorContext;
import org.co2dice.mirai.core.decorator.api.DecoratorModifier;
import org.co2dice.mirai.core.decorator.api.DecoratorValueInstance;

public abstract class SimpleEffectFuncPermanentDecorator<
		D extends Decorator<D, C, V>,
		C extends Record & DecoratorContext<C>,
		V extends Record & DecoratorValueInstance<V>
		> extends DecoratorModifier<D, C, V> {

	public boolean isValid() {
		return true;
	}

}
