package dev.lcy0x1.decorator.handler;

import dev.lcy0x1.decorator.api.Decorator;
import dev.lcy0x1.decorator.api.DecoratorContext;
import dev.lcy0x1.decorator.api.DecoratorHandler;
import dev.lcy0x1.decorator.api.DecoratorValueInstance;

import java.util.List;

/**
 * Package-private implementation of DecoratorHandler
 */
record DecoratorHandlerImpl<D extends Decorator<D, C, V>,
		C extends Record & DecoratorContext<C>,
		V extends Record & DecoratorValueInstance<V>>(
		DecoratorToken<D, C, V> token,
		List<D> list, int index
) implements DecoratorHandler<D, C, V> {

	public V apply(C context) {
		return index < 0 ? token.def().apply(context) : list.get(index).apply(getNext(), context);
	}

	private DecoratorHandlerImpl<D, C, V> getNext() {
		return new DecoratorHandlerImpl<>(token, list, index - 1);
	}

}
