package dev.lcy0x1.decorator.handler;

import dev.lcy0x1.decorator.api.Decorator;
import dev.lcy0x1.decorator.api.DecoratorContext;
import dev.lcy0x1.decorator.api.DecoratorValueInstance;
import dev.lcy0x1.decorator.instance.get_numeric_attribute.DefaultGetNumericAttributeDecorator;
import dev.lcy0x1.decorator.instance.get_numeric_attribute.GetNumericAttributeContext;
import dev.lcy0x1.decorator.instance.get_numeric_attribute.GetNumericAttributeDecorator;
import dev.lcy0x1.decorator.instance.get_numeric_attribute.GetNumericAttributeValueInstance;

import java.util.LinkedHashMap;

public class DecoratorRegistry {

	private static final LinkedHashMap<String, DecoratorToken<?, ?, ?>> REGISTRY = new LinkedHashMap<>();

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

	public static <D extends Decorator<D, C, V>,
			C extends Record & DecoratorContext<C>,
			V extends Record & DecoratorValueInstance<V>>
	DecoratorToken<D, C, V> registerDecorator(DecoratorToken<D, C, V> token) {
		REGISTRY.put(token.name(), token);
		return token;
	}

	@SuppressWarnings("unchecked")
	public static <D extends Decorator<D, C, V>,
			C extends Record & DecoratorContext<C>,
			V extends Record & DecoratorValueInstance<V>>
	DecoratorToken<D, C, V> getToken(String name) {
		return (DecoratorToken<D, C, V>) REGISTRY.get(name);
	}

	public static void register() {

	}

}
