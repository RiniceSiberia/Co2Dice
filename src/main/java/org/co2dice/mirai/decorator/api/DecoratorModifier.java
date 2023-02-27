package org.co2dice.mirai.decorator.api;


/**
 * //三个元件：修饰器本身，修饰器的逻辑，修饰器的数据，三者的集合实体
 * //和default不同,该类是当要给一个物体添加新的属性时添加的修饰器抽象实体
 */
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
