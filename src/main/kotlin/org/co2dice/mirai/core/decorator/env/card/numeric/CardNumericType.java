package org.co2dice.mirai.core.decorator.env.card.numeric;

import org.co2dice.mirai.core.bean.card.instance.CardInstance;
import org.co2dice.mirai.core.bean.api.CAO;
import org.co2dice.mirai.core.decorator.instance.card.numeric.GetCardNumericValueInstance;

import java.util.function.Function;
 /**
 * 属性的间接实体，记录了获取属性的方法，此处是返回数字类型的属性
 */
public final class CardNumericType {

//	@Deprecated
//	public static final CardNumericType ATK = new CardNumericType(e -> e.atk);
//	@Deprecated
//	public static final CardNumericType DEF = new CardNumericType(e -> e.def);
//
	public static final CardNumericType CHAOS = new CardNumericType(e -> {
		if (e instanceof CAO cao) {
			return cao.getChaos();
		} else {
			return null;
		}
	});

	public static final CardNumericType ORDER = new CardNumericType(e -> {
		if (e instanceof CAO cao) {
			return cao.getOrder();
		} else {
			return null;
		}
	});

	private final Function<CardInstance<?>, Integer> getter;

	private CardNumericType(Function<CardInstance<?>, Integer> getter) {
		this.getter = getter;
	}

	public GetCardNumericValueInstance getValue(CardInstance<?> target) {
		return new GetCardNumericValueInstance(getter.apply(target));
	}
}
