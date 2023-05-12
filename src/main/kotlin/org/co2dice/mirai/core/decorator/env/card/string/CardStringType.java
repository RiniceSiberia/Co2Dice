package org.co2dice.mirai.core.decorator.env.card.string;

import org.apache.commons.lang3.StringUtils;
import org.co2dice.mirai.core.bean.card.instance.CardInstance;
import org.co2dice.mirai.core.decorator.instance.card.string.GetCardStringValueInstance;

import java.util.function.Function;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2023-05-03-14:30
 * @Message: Have a good time!  :)
 **/
public final class CardStringType {
    public static final CardStringType NAME = new CardStringType(e -> e.getEntry().getCard().getCardRealName());

    public static final CardStringType ALIAS = new CardStringType(e -> e.getEntry().getCardAlias());

    public static final CardStringType TYPES = new CardStringType(e -> StringUtils.join(e.getEntry().getCard().getTypes(), "|" ));

    private final Function<CardInstance<?>, String> getter;

    private CardStringType(Function<CardInstance<?>, String> getter) {
        this.getter = getter;
    }

    public GetCardStringValueInstance getValue(CardInstance<?> target) {
        return new GetCardStringValueInstance(getter.apply(target));
    }
}
