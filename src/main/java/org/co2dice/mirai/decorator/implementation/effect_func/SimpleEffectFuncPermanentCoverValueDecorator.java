package org.co2dice.mirai.decorator.implementation.effect_func;


import kotlin.jvm.functions.Function1;
import org.co2dice.mirai.decorator.api.DecoratorHandler;
import org.co2dice.mirai.decorator.instance.get_effect_func_attribute.GetEffectFuncAttributeDecorator;
import org.co2dice.mirai.utils.Situation;
import org.co2dice.mirai.decorator.env.AttributeEffectFuncType;
import org.co2dice.mirai.decorator.instance.get_effect_func_attribute.GetEffectFuncAttributeContext;
import org.co2dice.mirai.decorator.instance.get_effect_func_attribute.GetEffectFuncAttributeValueInstance;

public final class SimpleEffectFuncPermanentCoverValueDecorator extends SimpleEffectFuncPermanentDecorator<
		GetEffectFuncAttributeDecorator, GetEffectFuncAttributeContext, GetEffectFuncAttributeValueInstance
		> implements GetEffectFuncAttributeDecorator {

	private final AttributeEffectFuncType type;
	private final Function1<Situation,Boolean> value;

	public SimpleEffectFuncPermanentCoverValueDecorator(AttributeEffectFuncType type, Function1<Situation,Boolean> value) {
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
