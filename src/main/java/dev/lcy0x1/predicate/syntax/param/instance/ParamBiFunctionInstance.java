package dev.lcy0x1.predicate.syntax.param.instance;

import dev.lcy0x1.predicate.api.instance.IBiFunction;
import dev.lcy0x1.predicate.syntax.param.type.IParam;
import dev.lcy0x1.predicate.syntax.type.OperandType;

public record ParamBiFunctionInstance<O, A, B>(
		OperandType<IBiFunction<O, A, B>> type,
		IParam param) implements IParamInstance<IBiFunction<O, A, B>> {

}
