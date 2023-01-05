package dev.lcy0x1.decorator.api;

/**
 * interface for decorator action requests.
 * This symbolizes the invocation process of function call.
 * The record should hold all parameter for the hypothetical function call.
 * <br>
 * Since records cannot be modified, modification of the context should return a
 * new context. This is intended.
 */
public interface DecoratorContext<C extends Record & DecoratorContext<C>> {
}
