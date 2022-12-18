package dev.lcy0x1.predicate.syntax.operation.instance.api;

import dev.lcy0x1.predicate.instance.IValueInstance;
import dev.lcy0x1.predicate.instance.PredicateContext;

public interface IOperationInstance {

	IValueInstance<?> toFunction(PredicateContext ctx);

}
