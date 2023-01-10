package org.co2dice.mirai.bean.game.decorator.api;

/**
 * Super-interface of decorator definitions.
 * All decorator base-interface should extend this interface directly.
 * Any subclass of Decorator should either extend DefaultDecorator or DecoratorModifier.
 */
// 修饰器接口，三个泛型为修饰器本身，修饰对象的记录内容（上下文）,修饰的
public interface Decorator<D extends Decorator<D, C, V>,
		C extends Record & DecoratorContext<C>,
		V extends Record & DecoratorValueInstance<V>> {

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
