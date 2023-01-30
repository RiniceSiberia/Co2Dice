package org.co2dice.mirai.bean.game.decorator.implementation.effect_func;


import kotlin.jvm.functions.Function4;
import org.co2dice.mirai.bean.cards.Cards;
import org.co2dice.mirai.bean.cards.api.EffectAPI;
import org.co2dice.mirai.bean.cards.character.CharacterCard;
import org.co2dice.mirai.bean.game.Scene;
import org.co2dice.mirai.bean.game.decorator.api.DecoratorHandler;
import org.co2dice.mirai.bean.game.decorator.env.AttributeEffectFuncType;
import org.co2dice.mirai.bean.game.decorator.instance.get_effect_func_attribute.GetEffectFuncAttributeContext;
import org.co2dice.mirai.bean.game.decorator.instance.get_effect_func_attribute.GetEffectFuncAttributeDecorator;
import org.co2dice.mirai.bean.game.decorator.instance.get_effect_func_attribute.GetEffectFuncAttributeValueInstance;

public final class SimpleEffectFuncPermanentCoverValueDecorator extends SimpleEffectFuncPermanentDecorator<
		GetEffectFuncAttributeDecorator, GetEffectFuncAttributeContext, GetEffectFuncAttributeValueInstance
		> implements GetEffectFuncAttributeDecorator {

	private final AttributeEffectFuncType type;
	private final Function4<Scene,Cards, CharacterCard, EffectAPI<Scene,Cards, CharacterCard>,Boolean> value;

	public SimpleEffectFuncPermanentCoverValueDecorator(AttributeEffectFuncType type, Function4<Scene,Cards, CharacterCard, EffectAPI<Scene,Cards, CharacterCard>,Boolean> value) {
		this.type = type;
		this.value = value;
	}

	@Override
	public GetEffectFuncAttributeValueInstance apply(
			DecoratorHandler<
                    GetEffectFuncAttributeDecorator,
                    GetEffectFuncAttributeContext,
                    GetEffectFuncAttributeValueInstance
                                > next,
			GetEffectFuncAttributeContext context
	) {
		GetEffectFuncAttributeValueInstance val = next.apply(context);
		if (context.type() == type) {
			return val.cover(value);
		}
		return val;
	}

}
