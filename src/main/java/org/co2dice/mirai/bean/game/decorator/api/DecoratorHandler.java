package org.co2dice.mirai.bean.game.decorator.api;

/**
 * A handler to allow performing actions through the decorators.
 * Can only be instantiated by DecoratorHolder.
 * Has only 1 public function: apply
 * //持有修饰器的对象，用于修饰器的调用
 * //apply方法为获取所有修饰器处理完毕后的总结果
 */
public interface DecoratorHandler<D extends Decorator<D, C, V>,
		C extends Record & DecoratorContext<C>,
		V extends Record & DecoratorValueInstance<V>> {

	/**
	 * Access the decorators.
	 * <br>
	 * It will send a request in the form of DecoratorContext from the last decorator to the first,
	 * where the decorators in the middle can modify the requests.
	 * The request will be ultimately handled by the default instance of the decorator type,
	 * registered with the DecoratorToken.
	 * Then, the returned value in the form of DecoratorValueInstance will be propagated from
	 * the first decorator to the last decorator, where the decorators in the middle
	 * may alter the value.
	 */
	V apply(C context);

}
