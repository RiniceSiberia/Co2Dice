package dev.lcy0x1.predicate.syntax.operation.action;

import dev.lcy0x1.predicate.instance.IValueInstance;
import dev.lcy0x1.predicate.instance.IValueInstanceList;
import dev.lcy0x1.predicate.instance.PredicateContext;

import java.util.List;

@FunctionalInterface
public interface IOperationList<O, I> extends IOperationSingle<O, List<I>> {

	default IValueInstance<O> apply(PredicateContext ctx, IValueInstance<List<I>> obj){
		return _apply(ctx, IValueInstanceList.asList(obj));
	}

	IValueInstance<O> _apply(PredicateContext ctx, IValueInstanceList<I> obj);

}
