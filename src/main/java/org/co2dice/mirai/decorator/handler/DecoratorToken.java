package org.co2dice.mirai.decorator.handler;

import org.co2dice.mirai.decorator.api.Decorator;
import org.co2dice.mirai.decorator.api.DecoratorContext;
import org.co2dice.mirai.decorator.api.DecoratorValueInstance;
import org.co2dice.mirai.decorator.api.DefaultDecorator;

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
