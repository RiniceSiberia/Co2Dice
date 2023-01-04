package dev.lcy0x1.decorator.handler;

import dev.lcy0x1.decorator.api.Decorator;
import dev.lcy0x1.decorator.api.DecoratorContext;
import dev.lcy0x1.decorator.api.DecoratorValueInstance;

import java.util.ArrayList;
import java.util.List;

public abstract class DecoratorHolder {

	private final List<Decorator<?, ?, ?>> list = new ArrayList<>();

	@SuppressWarnings("unchecked")
	public <D extends Decorator<D, C, V>,
			C extends Record & DecoratorContext<C>,
			V extends Record & DecoratorValueInstance<V>>
	DecoratorHandler<D, C, V> getHandler(DecoratorToken<D, C, V> token) {
		List<D> sublist = list.stream()
				.filter(e -> token.cls().isAssignableFrom(e.getClass()))
				.map(e -> (D) e).toList();
		return new DecoratorHandler<>(token, sublist, list.size() - 1);
	}

	public void addDecorator(Decorator<?, ?, ?> decorator) {
		list.add(decorator);
	}

	public void clean() {
		list.removeIf(e -> !e.isValid());
	}

}
