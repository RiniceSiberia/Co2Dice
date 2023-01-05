package dev.lcy0x1.decorator.api;

public abstract class DecoratorModifier<
		D extends Decorator<D, C, V>,
		C extends Record & DecoratorContext<C>,
		V extends Record & DecoratorValueInstance<V>
		> implements Decorator<D, C, V> {

	/**
	 * return false if this decorator is stale and should be removed
	 */
	public abstract boolean isValid();


}
