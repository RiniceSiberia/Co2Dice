package dev.lcy0x1.decorator.api;

import dev.lcy0x1.decorator.handler.DecoratorHandler;

public interface Decorator<D extends Decorator<D, C, V>, C extends Record & DecoratorContext<C>, V extends Record & DecoratorValueInstance<V>> {

	boolean isValid();

	V apply(DecoratorHandler<D, C, V> next, C context);

}
