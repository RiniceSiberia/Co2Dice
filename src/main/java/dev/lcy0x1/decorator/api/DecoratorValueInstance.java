package dev.lcy0x1.decorator.api;

/**
 * interface for decorator action responses.
 * This symbolizes the return process of function call.
 * The record should hold all return values for the hypothetical function call.
 * <br>
 * Since records cannot be modified, modification of the value instance should return a
 * new value instance. This is intended.
 */
public interface DecoratorValueInstance<V extends Record & DecoratorValueInstance<V>> {
}
