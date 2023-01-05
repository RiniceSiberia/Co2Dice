package dev.lcy0x1.decorator.handler;

import dev.lcy0x1.decorator.api.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The superclass of all objects that can hold decorators.
 * To access decorators, get a DecoratorHandler with a DecoratorToken.
 * Provides methods to add decorators and remove stale decorators
 */
public abstract class DecoratorHolder {

	private final List<DecoratorModifier<?, ?, ?>> list = new ArrayList<>();

	/**
	 * get a DecoratorHandler by providing the corresponding DecoratorToken. Primarily used to access decorators.
	 */
	@SuppressWarnings("unchecked")
	public <D extends Decorator<D, C, V>,
			C extends Record & DecoratorContext<C>,
			V extends Record & DecoratorValueInstance<V>>
	DecoratorHandler<D, C, V> getHandler(DecoratorToken<D, C, V> token) {
		List<D> sublist = list.stream()
				.filter(e -> token.cls().isAssignableFrom(e.getClass()))
				.map(e -> (D) e).toList();
		return new DecoratorHandlerImpl<>(token, sublist, list.size() - 1);
	}

	/**
	 * add a new decorator
	 */
	public void addDecorator(DecoratorModifier<?, ?, ?> decorator) {
		list.add(decorator);
	}

	/**
	 * remove stale decorators.
	 */
	public void clean() {
		list.removeIf(e -> !e.isValid());
	}

}
