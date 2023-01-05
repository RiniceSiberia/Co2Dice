package dev.lcy0x1.decorator.api;

/**
 * Super-interface of decorator definitions.
 * All decorator base-interface should extend this interface directly.
 * Any subclass of Decorator should either extend DefaultDecorator or DecoratorModifier.
 */
public interface Decorator<D extends Decorator<D, C, V>, C extends Record & DecoratorContext<C>, V extends Record & DecoratorValueInstance<V>> {

	/**
	 * cast this object
	 */
	@SuppressWarnings("unchecked")
	default D getThis() {
		return (D) this;
	}

	/**
	 * apply the decorator if the context match the requirement
	 */
	V apply(DecoratorHandler<D, C, V> next, C context);

}
