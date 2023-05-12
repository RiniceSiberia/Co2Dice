package org.co2dice.mirai.core.decorator.implementation.chess.numeric;

import org.co2dice.mirai.core.decorator.api.DecoratorHandler;
import org.co2dice.mirai.core.decorator.env.chess.numeric.ChessNumericType;
import org.co2dice.mirai.core.decorator.implementation.chess.ChessPermanentDecorator;
import org.co2dice.mirai.core.decorator.instance.chess.numeric.GetChessNumericContext;
import org.co2dice.mirai.core.decorator.instance.chess.numeric.GetChessNumericDecorator;
import org.co2dice.mirai.core.decorator.instance.chess.numeric.GetChessNumericValueInstance;

/**
 * @author lcy0x1
 */
public final class ChessNumericPermanentMultiplyValueDecorator extends ChessPermanentDecorator<
		GetChessNumericDecorator, GetChessNumericContext, GetChessNumericValueInstance
		> implements GetChessNumericDecorator {

	private final ChessNumericType type;
	private final int value;

	public ChessNumericPermanentMultiplyValueDecorator(ChessNumericType type, int value) {
		this.type = type;
		this.value = value;
	}

	@Override
	public GetChessNumericValueInstance apply(
			DecoratorHandler<
					GetChessNumericDecorator,
					GetChessNumericContext,
					GetChessNumericValueInstance> next,
			GetChessNumericContext context
	) {
		GetChessNumericValueInstance val = next.apply(context);
		if (context.type() == type) {
			return val.multiply(value);
		}
		return val;
	}

	public ChessNumericType getType() {
		return type;
	}

	public int getValue() {
		return value;
	}
}
