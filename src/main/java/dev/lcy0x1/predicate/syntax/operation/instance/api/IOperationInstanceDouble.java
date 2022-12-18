package dev.lcy0x1.predicate.syntax.operation.instance.api;

import dev.lcy0x1.predicate.api.instance.IBiFunction;
import dev.lcy0x1.predicate.instance.IValueInstance;
import dev.lcy0x1.predicate.instance.PredicateContext;
import dev.lcy0x1.predicate.syntax.type.OperandType;
import dev.lcy0x1.predicate.syntax.type.OperandTypes;

public interface IOperationInstanceDouble<O, A, B> extends IOperationInstance {

	OperandType<O> getOutputType();

	OperandType<A> getFirstType();

	OperandType<B> getSecondType();

	default IValueInstance<IBiFunction<O, A, B>> toFunction(PredicateContext ctx) {
		var type = OperandTypes.biFunctionType(getOutputType(), getFirstType(), getSecondType());
		return ctx.buildInstance(type, c -> c.buildChildren(this));
	}

}
