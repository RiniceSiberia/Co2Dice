package org.co2dice.mirai.bean.game.decorator.instance.get_effect_func_attribute;


import kotlin.jvm.functions.Function4;
import org.co2dice.mirai.bean.cards.Cards;
import org.co2dice.mirai.bean.cards.api.EffectAPI;
import org.co2dice.mirai.bean.cards.character.CharacterCard;
import org.co2dice.mirai.bean.cards.effect.Effect;
import org.co2dice.mirai.bean.game.Scene;
import org.co2dice.mirai.bean.game.decorator.api.DecoratorValueInstance;

public record GetEffectFuncAttributeValueInstance(
		Function4<Scene,Cards, CharacterCard, EffectAPI<Scene,Cards, CharacterCard>,Boolean> value)
		implements DecoratorValueInstance<GetEffectFuncAttributeValueInstance> {

	public GetEffectFuncAttributeValueInstance add(Function4<Scene,Cards, CharacterCard, EffectAPI<Scene,Cards, CharacterCard>,Boolean> addEffect) {
		return new GetEffectFuncAttributeValueInstance(
				//在原来效果的下面复写一句(注：如果原效果未处理成功，则附加效果无法触发)
				(scene,cards,character,effect) -> value().invoke(scene, cards, character,effect)
				&& addEffect.invoke(scene,cards,character,effect));
	}

	public GetEffectFuncAttributeValueInstance cover(Function4<Scene,Cards, CharacterCard, EffectAPI<Scene,Cards, CharacterCard>,Boolean> coverEffect) {
		return new GetEffectFuncAttributeValueInstance(coverEffect);
	}

	public GetEffectFuncAttributeValueInstance negate() {
		return new GetEffectFuncAttributeValueInstance(
				(scene, cards, characterCard,effect) -> false);
	}

}
