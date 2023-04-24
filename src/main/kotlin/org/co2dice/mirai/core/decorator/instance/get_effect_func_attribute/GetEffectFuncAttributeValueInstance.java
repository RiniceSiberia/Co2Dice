package org.co2dice.mirai.core.decorator.instance.get_effect_func_attribute;


import kotlin.jvm.functions.Function1;
import org.co2dice.mirai.core.bean.effect.utils.Situation;
import org.co2dice.mirai.core.decorator.api.DecoratorValueInstance;

public record GetEffectFuncAttributeValueInstance(
		Function1<Situation,Boolean> func)
		implements DecoratorValueInstance<GetEffectFuncAttributeValueInstance> {

	public GetEffectFuncAttributeValueInstance add(Function1<Situation,Boolean> addEffect) {
		return new GetEffectFuncAttributeValueInstance(
				//在原来效果的下面复写一句(注：如果原效果未处理成功，则附加效果无法触发)
				(situation) -> this.func().invoke(situation)
				&& addEffect.invoke(situation));
	}

	public GetEffectFuncAttributeValueInstance cover(Function1<Situation,Boolean> coverEffect) {
		return new GetEffectFuncAttributeValueInstance(coverEffect);
	}

	public GetEffectFuncAttributeValueInstance negate() {
		return new GetEffectFuncAttributeValueInstance(
				(situation) -> false);
	}

}
