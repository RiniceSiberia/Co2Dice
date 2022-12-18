package dev.lcy0x1.predicate.syntax.operation.action;

import dev.lcy0x1.predicate.instance.IValueInstance;
import dev.lcy0x1.predicate.instance.PredicateContext;

public interface IOperationSingle<O, I> {

	IValueInstance<O> apply(PredicateContext ctx, IValueInstance<I> obj);

}
