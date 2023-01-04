package dev.lcy0x1.decorator.handler;

import dev.lcy0x1.decorator.api.Decorator;
import dev.lcy0x1.decorator.api.DecoratorContext;
import dev.lcy0x1.decorator.api.DecoratorValueInstance;
import dev.lcy0x1.decorator.api.DefaultDecorator;

public record DecoratorToken<D extends Decorator<D, C, V>,
		C extends Record & DecoratorContext<C>,
		V extends Record & DecoratorValueInstance<V>>(
		String name,
		Class<D> cls,
		DefaultDecorator<D, C, V> def
) {

}
