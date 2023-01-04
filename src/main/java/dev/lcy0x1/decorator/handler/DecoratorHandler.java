package dev.lcy0x1.decorator.handler;

import dev.lcy0x1.decorator.api.Decorator;
import dev.lcy0x1.decorator.api.DecoratorContext;
import dev.lcy0x1.decorator.api.DecoratorValueInstance;

import java.util.List;

public final class DecoratorHandler<D extends Decorator<D, C, V>,
		C extends Record & DecoratorContext<C>,
		V extends Record & DecoratorValueInstance<V>> {

	private final DecoratorToken<D, C, V> token;
	private final List<D> list;
	private final int index;

	DecoratorHandler(DecoratorToken<D, C, V> token, List<D> list, int index) {
		this.token = token;
		this.list = list;
		this.index = index;
	}

	public V apply(C context) {
		return index < 0 ? token.def().apply(context) : list.get(index).apply(getNext(), context);
	}

	private DecoratorHandler<D, C, V> getNext() {
		return new DecoratorHandler<>(token, list, index - 1);
	}

}
