package dev.lcy0x1.predicate.syntax.param.instance;

import dev.lcy0x1.predicate.api.instance.IFunction;
import dev.lcy0x1.predicate.syntax.param.type.IParam;
import dev.lcy0x1.predicate.syntax.type.OperandType;

public record ParamFunctionInstance<O, I>(
		OperandType<IFunction<O, I>> type,
		IParam param) implements IParamInstance<IFunction<O, I>> {

}
