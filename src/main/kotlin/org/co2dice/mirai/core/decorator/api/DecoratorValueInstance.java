package org.co2dice.mirai.core.decorator.api;

/**
 * interface for decorator action responses.
 * This symbolizes the return process of function call.
 * The record should hold all return values for the hypothetical function call.
 * <br>
 * Since records cannot be modified, modification of the value instance should return a
 * new value instance. This is intended.
 * //作用为装饰器的返回值
 * //如果你把decorator当做一个function call，context就是parameter，value instance就是return value
 * @author lcy0x1
 */
public interface DecoratorValueInstance<V extends Record & DecoratorValueInstance<V>> {
}
