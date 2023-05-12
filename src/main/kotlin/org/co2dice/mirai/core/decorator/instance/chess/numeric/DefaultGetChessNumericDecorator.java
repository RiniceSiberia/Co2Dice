package org.co2dice.mirai.core.decorator.instance.chess.numeric;

import org.co2dice.mirai.core.decorator.api.DefaultDecorator;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2023-04-30-22:17
 * @Message: Have a good time!  :)
 **/
public final class DefaultGetChessNumericDecorator
        extends DefaultDecorator<GetChessNumericDecorator,
        GetChessNumericContext,
        GetChessNumericValueInstance>
        implements GetChessNumericDecorator {

    @Override
    public GetChessNumericValueInstance apply(GetChessNumericContext context) {
        return context.type().getValue(context.target());
    }
}
