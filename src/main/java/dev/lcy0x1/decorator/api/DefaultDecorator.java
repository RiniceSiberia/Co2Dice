package dev.lcy0x1.decorator.api;

import dev.lcy0x1.decorator.handler.DecoratorHandler;

public abstract class DefaultDecorator<D extends Decorator<D, C, V>,
		C extends Record & DecoratorContext<C>,
		V extends Record & DecoratorValueInstance<V>>
		implements Decorator<D, C, V> {

	@SuppressWarnings("unchecked")
	public final D getAsDecorator() {
		return (D) this;
	}

	@Override
	public boolean isValid() {
		return true;
	}

	@Override
	public final V apply(DecoratorHandler<D, C, V> next, C context) {
		return apply(context);
	}

	public abstract V apply(C context);
}
