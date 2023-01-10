package org.co2dice.mirai.bean.game.decorator.env;

import org.co2dice.mirai.bean.cards.Cards;
import org.co2dice.mirai.bean.cards.character.CharacterCard;
import org.co2dice.mirai.bean.cards.event.EventCard;
import org.co2dice.mirai.bean.cards.item.ItemCard;
import org.co2dice.mirai.bean.cards.skill.SkillCard;
import org.co2dice.mirai.bean.game.decorator.instance.get_numeric_attribute.GetNumericAttributeValueInstance;

import java.util.function.Function;

public final class AttributeNumericType {

//	@Deprecated
//	public static final AttributeNumericType ATK = new AttributeNumericType(e -> e.atk);
//	@Deprecated
//	public static final AttributeNumericType DEF = new AttributeNumericType(e -> e.def);
//
	public static final AttributeNumericType CHAOS = new AttributeNumericType(e -> {
		if (e instanceof ItemCard itemCard) return itemCard.getChaos();
		else if (e instanceof SkillCard skillCard) return skillCard.getChaos();
		else if (e instanceof CharacterCard || e instanceof EventCard) return -1;
		else return -1;
	});

	public static final AttributeNumericType ORDER = new AttributeNumericType(e -> {
		if (e instanceof ItemCard itemCard) return itemCard.getOrder();
		else if (e instanceof SkillCard skillCard) return skillCard.getOrder();
		else if (e instanceof CharacterCard || e instanceof EventCard) return -1;
		else return -1;
	});

	private final Function<Cards, Integer> getter;

	private AttributeNumericType(Function<Cards, Integer> getter) {
		this.getter = getter;
	}

	public GetNumericAttributeValueInstance getValue(Cards target) {
		return new GetNumericAttributeValueInstance(getter.apply(target));
	}
}
