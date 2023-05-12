package org.co2dice.mirai.core.decorator.instance.chess.numeric;

import org.co2dice.mirai.core.bean.chessman.instance.ChessmanInstance;
import org.co2dice.mirai.core.decorator.api.DecoratorContext;
import org.co2dice.mirai.core.decorator.env.chess.numeric.ChessNumericType;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2023-04-30-21:58
 * @Message: Have a good time!  :)
 **/
public record GetChessNumericContext(ChessNumericType type, ChessmanInstance target)
        implements DecoratorContext<GetChessNumericContext> {
    public GetChessNumericContext withStat(ChessNumericType type) {
        return new GetChessNumericContext(type, target);
    }
}
