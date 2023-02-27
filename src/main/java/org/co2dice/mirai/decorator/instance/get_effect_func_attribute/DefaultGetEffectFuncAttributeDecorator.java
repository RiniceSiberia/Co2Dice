package org.co2dice.mirai.decorator.instance.get_effect_func_attribute;

import org.co2dice.mirai.decorator.api.DefaultDecorator;

public final class DefaultGetEffectFuncAttributeDecorator
		extends DefaultDecorator<GetEffectFuncAttributeDecorator,
                GetEffectFuncAttributeContext,
                GetEffectFuncAttributeValueInstance>
		implements GetEffectFuncAttributeDecorator {

	@Override
	public GetEffectFuncAttributeValueInstance apply(GetEffectFuncAttributeContext context) {
		return context.type().getFunc(context.target(),context.index());
	}

}
