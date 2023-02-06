package org.co2dice.mirai.bean.game.decorator.env;

import org.co2dice.mirai.bean.cards.CardsInstance;
import org.co2dice.mirai.bean.cards.api.CAO;
import org.co2dice.mirai.bean.game.decorator.instance.get_numeric_attribute.GetNumericAttributeValueInstance;

import java.util.function.Function;
 /**
 * 属性的间接实体，记录了获取属性的方法，此处是返回数字类型的属性
 */
public final class AttributeNumericType {

//	@Deprecated
//	public static final AttributeNumericType ATK = new AttributeNumericType(e -> e.atk);
//	@Deprecated
//	public static final AttributeNumericType DEF = new AttributeNumericType(e -> e.def);
//
	public static final AttributeNumericType CHAOS = new AttributeNumericType(e -> {
		if (e instanceof CAO cao) return cao.getChaos();
		else return null;
	});

	public static final AttributeNumericType ORDER = new AttributeNumericType(e -> {
		if (e instanceof CAO cao) return cao.getOrder();
		else return null;
	});

	private final Function<CardsInstance, Integer> getter;

	private AttributeNumericType(Function<CardsInstance, Integer> getter) {
		this.getter = getter;
	}

	public GetNumericAttributeValueInstance getValue(CardsInstance target) {
		return new GetNumericAttributeValueInstance(getter.apply(target));
	}
}
