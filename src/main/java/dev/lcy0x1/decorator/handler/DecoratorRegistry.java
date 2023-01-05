package dev.lcy0x1.decorator.handler;

import dev.lcy0x1.decorator.api.Decorator;
import dev.lcy0x1.decorator.api.DecoratorContext;
import dev.lcy0x1.decorator.api.DecoratorValueInstance;
import dev.lcy0x1.decorator.instance.get_numeric_attribute.DefaultGetNumericAttributeDecorator;
import dev.lcy0x1.decorator.instance.get_numeric_attribute.GetNumericAttributeContext;
import dev.lcy0x1.decorator.instance.get_numeric_attribute.GetNumericAttributeDecorator;
import dev.lcy0x1.decorator.instance.get_numeric_attribute.GetNumericAttributeValueInstance;

import java.util.LinkedHashMap;

/**
 * registry class that provides decorator token instance. Call register() to initialize
 */
public class DecoratorRegistry {

	/**
	 * registry of the decorator token, allowing serialization
	 */
	private static final LinkedHashMap<Class<?>, DecoratorToken<?, ?, ?>> REGISTRY = new LinkedHashMap<>();

	/**
	 * the decorator for getting numeric attributes of a card
	 */
	public static final DecoratorToken<
			GetNumericAttributeDecorator,
			GetNumericAttributeContext,
			GetNumericAttributeValueInstance
			> GET_NUMERIC_ATTRIBUTE;

	static {
		GET_NUMERIC_ATTRIBUTE = registerDecorator(new DecoratorToken<>(
				"get_numeric_attribute",
				GetNumericAttributeDecorator.class,
				new DefaultGetNumericAttributeDecorator()));
	}

	/**
	 * register a decorator token into the registry
	 */
	public static <D extends Decorator<D, C, V>,
			C extends Record & DecoratorContext<C>,
			V extends Record & DecoratorValueInstance<V>>
	DecoratorToken<D, C, V> registerDecorator(DecoratorToken<D, C, V> token) {
		assert !REGISTRY.containsKey(token.cls());
		REGISTRY.put(token.cls(), token);
		return token;
	}

	/**
	 * get a token from the registry, for serialization purpose
	 */
	@SuppressWarnings("unchecked")
	public static <D extends Decorator<D, C, V>,
			C extends Record & DecoratorContext<C>,
			V extends Record & DecoratorValueInstance<V>>
	DecoratorToken<D, C, V> getToken(Class<D> cls) {
		return (DecoratorToken<D, C, V>) REGISTRY.get(cls);
	}

	/**
	 * Invoke the static class initializer (the static {} block). Must be called before everything.
	 */
	public static void register() {

	}

}
