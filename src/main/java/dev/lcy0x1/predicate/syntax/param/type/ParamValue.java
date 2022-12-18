package dev.lcy0x1.predicate.syntax.param.type;

import dev.lcy0x1.predicate.instance.IValueInstance;
import dev.lcy0x1.predicate.syntax.param.instance.IParamInstance;
import dev.lcy0x1.predicate.syntax.type.OperandType;

public record ParamValue<T>(OperandType<T> type, String name)
		implements IParam, IParamInstance<T> {

	@Override
	public IParamInstance<T> build(IValueInstance<?> val) {
		return this;
	}

	@Override
	public IParam param() {
		return this;
	}

}
