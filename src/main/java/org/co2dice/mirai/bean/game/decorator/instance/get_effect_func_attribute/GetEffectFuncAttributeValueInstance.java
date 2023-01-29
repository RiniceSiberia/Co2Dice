package org.co2dice.mirai.bean.game.decorator.instance.get_effect_func_attribute;

import kotlin.jvm.functions.Function3;
import org.co2dice.mirai.bean.cards.Cards;
import org.co2dice.mirai.bean.cards.character.CharacterCard;
import org.co2dice.mirai.bean.cards.effect.Effect;
import org.co2dice.mirai.bean.game.Scene;
import org.co2dice.mirai.bean.game.decorator.api.DecoratorValueInstance;

public record GetEffectFuncAttributeValueInstance(Function3<Scene,Cards, CharacterCard,Boolean> value)
		implements DecoratorValueInstance<GetEffectFuncAttributeValueInstance> {

	public GetEffectFuncAttributeValueInstance add(Function3<Scene,Cards, CharacterCard,Boolean> addEffect) {
		return new GetEffectFuncAttributeValueInstance(
				//在原来效果的下面复写一句
				(scene,cards,character) -> value().invoke(scene, cards, character)
				&& addEffect.invoke(scene,cards,character));
	}

	public GetEffectFuncAttributeValueInstance cover(Function3<Scene,Cards, CharacterCard,Boolean> coverEffect) {
		return new GetEffectFuncAttributeValueInstance(coverEffect);
	}

}
