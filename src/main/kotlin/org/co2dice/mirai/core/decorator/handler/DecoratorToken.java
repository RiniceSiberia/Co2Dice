package org.co2dice.mirai.core.decorator.handler;

import org.co2dice.mirai.core.decorator.api.Decorator;
import org.co2dice.mirai.core.decorator.api.DecoratorContext;
import org.co2dice.mirai.core.decorator.api.DecoratorValueInstance;
import org.co2dice.mirai.core.decorator.api.DefaultDecorator;

/**
 * A token that represents a type of decorator.
 * Holds the name for serialization,
 * the base decorator class,
 * and the default instance of the decorator.
 */
public record DecoratorToken<D extends Decorator<D, C, V>,
		C extends Record & DecoratorContext<C>,
		V extends Record & DecoratorValueInstance<V>>(
		String name,
		Class<D> cls,
		DefaultDecorator<D, C, V> def
) {

}
