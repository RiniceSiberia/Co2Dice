package dev.lcy0x1.predicate.syntax.operation.instance.complex;

import dev.lcy0x1.predicate.instance.IValueInstance;
import dev.lcy0x1.predicate.instance.PredicateContext;
import dev.lcy0x1.predicate.syntax.operation.instance.api.IOperationInstance;

public class FunctionOperationInstance implements IOperationInstance {

	@Override
	public IValueInstance<?> toFunction(PredicateContext ctx) {//TODO
		throw new RuntimeException("function cannot be converted to function");
	}

}
