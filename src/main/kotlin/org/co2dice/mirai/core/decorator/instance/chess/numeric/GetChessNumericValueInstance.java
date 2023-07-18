package org.co2dice.mirai.core.decorator.instance.chess.numeric;

import org.co2dice.mirai.core.decorator.api.DecoratorValueInstance;

/**
 * 使用IDEA编写
 *
 * {@code @Author:} DUELIST
 * {@code @Time:} 2023-04-30-22:14
 * {@code @Message:} Have a good time!  :)
 **/
public record GetChessNumericValueInstance(Integer value)
        implements DecoratorValueInstance<GetChessNumericValueInstance> {

    public GetChessNumericValueInstance add(int value) {
        return new GetChessNumericValueInstance(this.value + value);
    }

    public GetChessNumericValueInstance multiply(int value) {
        return new GetChessNumericValueInstance((int) Math.ceil(this.value * value));
    }

    public GetChessNumericValueInstance divide(int value) {
        return new GetChessNumericValueInstance(this.value / value);
    }

}
