package org.co2dice.mirai.core.decorator.implementation.chess;

import org.co2dice.mirai.core.decorator.api.Decorator;
import org.co2dice.mirai.core.decorator.api.DecoratorContext;
import org.co2dice.mirai.core.decorator.api.DecoratorModifier;
import org.co2dice.mirai.core.decorator.api.DecoratorValueInstance;

/**
 * 使用IDEA编写
 *
 * {@code @Author:} DUELIST
 * {@code @Time:} 2023-04-30-22:43
 * {@code @Message:} Have a good time!  :)
 **/
public abstract class ChessPermanentDecorator<
        D extends Decorator<D, C, V>,
        C extends Record & DecoratorContext<C>,
        V extends Record & DecoratorValueInstance<V>
        > extends DecoratorModifier<D, C, V> {

    @Override
    public boolean isValid() {
        return true;
    }

}
