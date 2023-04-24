package org.co2dice.mirai.core.decorator.api;

/**
 * default value for decorator. Serves as the last handler of context and first producer of value instance.
 * //默认的修饰器实体,继承了三个元件：修饰器本身，修饰器的逻辑，修饰器的数据，三者的集合实体
 * //物体最基础的属性的获取的方式，比如获取一个怪物的攻击，假设他的攻击力没有受到其他卡的影响，那么get就会获取到这个
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
