package dev.lcy0x1.predicate.syntax.operation.instance.api;

import dev.lcy0x1.predicate.api.instance.IFunction;
import dev.lcy0x1.predicate.instance.IValueInstance;
import dev.lcy0x1.predicate.instance.PredicateContext;
import dev.lcy0x1.predicate.syntax.type.OperandType;
import dev.lcy0x1.predicate.syntax.type.OperandTypes;

public interface IOperationInstanceSingle<O, I> extends IOperationInstance {

	OperandType<O> getOutputType();

	OperandType<I> getParameterType();

	default IValueInstance<IFunction<O, I>> toFunction(PredicateContext ctx) {
		var type = OperandTypes.functionType(getOutputType(), getParameterType());
		return ctx.buildInstance(type, c -> c.buildChildren(this));
	}

}
