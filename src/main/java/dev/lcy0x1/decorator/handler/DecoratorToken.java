package dev.lcy0x1.decorator.handler;

import dev.lcy0x1.decorator.api.Decorator;
import dev.lcy0x1.decorator.api.DecoratorContext;
import dev.lcy0x1.decorator.api.DecoratorValueInstance;
import dev.lcy0x1.decorator.api.DefaultDecorator;

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
