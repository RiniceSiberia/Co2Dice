package dev.lcy0x1.predicate.syntax.operation.action;

import dev.lcy0x1.predicate.instance.IValueInstance;
import dev.lcy0x1.predicate.instance.PredicateContext;

public interface IOperationDouble<O, A, B> {

	IValueInstance<O> apply(PredicateContext ctx, IValueInstance<A> a, IValueInstance<B> b);

}
