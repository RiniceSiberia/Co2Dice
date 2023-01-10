package org.co2dice.mirai.bean.game.decorator.api;

/**
 * default value for decorator. Serves as the last handler of context and first producer of value instance.
 */
public abstract class DefaultDecorator<D extends Decorator<D, C, V>,
		C extends Record & DecoratorContext<C>,
		V extends Record & DecoratorValueInstance<V>>
		implements Decorator<D, C, V> {

	@Override
	public final V apply(DecoratorHandler<D, C, V> next, C context) {
		return apply(context);
	}

	public abstract V apply(C context);

}
