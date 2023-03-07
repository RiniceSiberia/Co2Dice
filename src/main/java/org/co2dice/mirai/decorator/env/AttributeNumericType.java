package org.co2dice.mirai.decorator.env;

import org.co2dice.mirai.bean.card.instance.CardInstance;
import org.co2dice.mirai.bean.API.CAO;
import org.co2dice.mirai.decorator.instance.get_numeric_attribute.GetNumericAttributeValueInstance;

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

	private final Function<CardInstance, Integer> getter;

	private AttributeNumericType(Function<CardInstance, Integer> getter) {
		this.getter = getter;
	}

	public GetNumericAttributeValueInstance getValue(CardInstance target) {
		return new GetNumericAttributeValueInstance(getter.apply(target));
	}
}