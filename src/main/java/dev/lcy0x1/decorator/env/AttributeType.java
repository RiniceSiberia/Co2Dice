package dev.lcy0x1.decorator.env;

import dev.lcy0x1.decorator.instance.get_numeric_attribute.GetNumericAttributeValueInstance;

import java.util.function.Function;

public final class AttributeType {

	public static final AttributeType ATK = new AttributeType(e -> e.atk);
	public static final AttributeType DEF = new AttributeType(e -> e.def);

	private final Function<Card, Integer> getter;

	private AttributeType(Function<Card, Integer> getter) {
		this.getter = getter;
	}

	public GetNumericAttributeValueInstance getValue(Card target) {
		return new GetNumericAttributeValueInstance(getter.apply(target));
	}
}
