package org.co2dice.mirai.core.decorator.handler;

import org.co2dice.mirai.core.decorator.api.Decorator;
import org.co2dice.mirai.core.decorator.api.DecoratorContext;
import org.co2dice.mirai.core.decorator.api.DecoratorValueInstance;
import org.co2dice.mirai.core.decorator.instance.card.numeric.DefaultGetCardNumericDecorator;
import org.co2dice.mirai.core.decorator.instance.card.numeric.GetCardNumericContext;
import org.co2dice.mirai.core.decorator.instance.card.numeric.GetCardNumericDecorator;
import org.co2dice.mirai.core.decorator.instance.card.numeric.GetCardNumericValueInstance;
import org.co2dice.mirai.core.decorator.instance.chess.numeric.DefaultGetChessNumericDecorator;
import org.co2dice.mirai.core.decorator.instance.chess.numeric.GetChessNumericContext;
import org.co2dice.mirai.core.decorator.instance.chess.numeric.GetChessNumericDecorator;
import org.co2dice.mirai.core.decorator.instance.chess.numeric.GetChessNumericValueInstance;

import java.util.LinkedHashMap;

/**
 * registry class that provides decorator token instance. Call register() to initialize
 * @author lcy0x1
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
			GetCardNumericDecorator,
			GetCardNumericContext,
			GetCardNumericValueInstance> GET_CARD_NUMERIC;

	public static final DecoratorToken<
			GetChessNumericDecorator,
			GetChessNumericContext,
			GetChessNumericValueInstance> GET_CHESS_NUMERIC;

	static {
		GET_CARD_NUMERIC = registerDecorator(new DecoratorToken<>(
				"get_card_numeric",
				GetCardNumericDecorator.class,
				new DefaultGetCardNumericDecorator()));

		GET_CHESS_NUMERIC = registerDecorator(new DecoratorToken<>(
				"get_chess_numeric",
				GetChessNumericDecorator.class,
				new DefaultGetChessNumericDecorator()));
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
