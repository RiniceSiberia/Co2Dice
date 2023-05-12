package org.co2dice.mirai.core.decorator.handler;

import org.co2dice.mirai.core.decorator.api.Decorator;
import org.co2dice.mirai.core.decorator.api.DecoratorContext;
import org.co2dice.mirai.core.decorator.api.DecoratorHandler;
import org.co2dice.mirai.core.decorator.api.DecoratorValueInstance;

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

	@Override
	public V apply(C context) {
		return index < 0 ? token.def().apply(context) : list.get(index).apply(getNext(), context);
	}

	private DecoratorHandlerImpl<D, C, V> getNext() {
		return new DecoratorHandlerImpl<>(token, list, index - 1);
	}

}
