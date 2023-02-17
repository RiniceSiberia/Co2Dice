package org.co2dice.mirai.bean.game.decorator.implementation.effect_func;



import kotlin.jvm.functions.Function1;
import org.co2dice.mirai.bean.game.instance.card.Situation;
import org.co2dice.mirai.bean.game.decorator.api.DecoratorHandler;
import org.co2dice.mirai.bean.game.decorator.env.AttributeEffectFuncType;
import org.co2dice.mirai.bean.game.decorator.instance.get_effect_func_attribute.GetEffectFuncAttributeContext;
import org.co2dice.mirai.bean.game.decorator.instance.get_effect_func_attribute.GetEffectFuncAttributeDecorator;
import org.co2dice.mirai.bean.game.decorator.instance.get_effect_func_attribute.GetEffectFuncAttributeValueInstance;

public final class SimpleEffectFuncPermanentAddValueDecorator extends SimpleEffectFuncPermanentDecorator<
		GetEffectFuncAttributeDecorator, GetEffectFuncAttributeContext, GetEffectFuncAttributeValueInstance
		> implements GetEffectFuncAttributeDecorator {

	private final AttributeEffectFuncType type;
	private final Function1<Situation,Boolean> value;

	public SimpleEffectFuncPermanentAddValueDecorator(AttributeEffectFuncType type, Function1<Situation,Boolean> value) {
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
			return val.add(value);
		}
		return val;
	}

	public Function1<Situation,Boolean> value() {
		return value;
	}

}
