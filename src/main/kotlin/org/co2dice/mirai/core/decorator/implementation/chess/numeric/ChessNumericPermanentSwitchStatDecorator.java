package org.co2dice.mirai.core.decorator.implementation.chess.numeric;

import org.co2dice.mirai.core.decorator.api.DecoratorHandler;
import org.co2dice.mirai.core.decorator.env.chess.numeric.ChessNumericType;
import org.co2dice.mirai.core.decorator.implementation.chess.ChessPermanentDecorator;
import org.co2dice.mirai.core.decorator.instance.chess.numeric.GetChessNumericContext;
import org.co2dice.mirai.core.decorator.instance.chess.numeric.GetChessNumericDecorator;
import org.co2dice.mirai.core.decorator.instance.chess.numeric.GetChessNumericValueInstance;

/**
 * @author DUELIST
 */
public final class ChessNumericPermanentSwitchStatDecorator extends ChessPermanentDecorator<
		GetChessNumericDecorator, GetChessNumericContext, GetChessNumericValueInstance
		> implements GetChessNumericDecorator {

	private final ChessNumericType from;
	private final ChessNumericType to;

	public ChessNumericPermanentSwitchStatDecorator(ChessNumericType from, ChessNumericType to) {
		this.from = from;
		this.to = to;
	}

	@Override
	public GetChessNumericValueInstance apply(
			DecoratorHandler<
								GetChessNumericDecorator,
								GetChessNumericContext,
								GetChessNumericValueInstance> next,
			GetChessNumericContext context) {
		GetChessNumericValueInstance val = next.apply(context);
		if (context.type() == from){
			return next.apply(context.withStat(to));
		}
		return val;
	}

}
