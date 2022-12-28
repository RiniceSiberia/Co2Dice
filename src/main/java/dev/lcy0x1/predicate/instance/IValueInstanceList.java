package dev.lcy0x1.predicate.instance;

import dev.lcy0x1.predicate.syntax.type.OperandType;
import dev.lcy0x1.util.CastHelper;

import java.util.List;

public interface IValueInstanceList<T> extends IValueInstance<List<T>> {

	static <I> IValueInstanceList<I> asList(IValueInstance<List<I>> obj) {
		return CastHelper.castList(obj);
	}

	OperandType<List<T>> getType();

	List<ValueInstanceConstant<T>> unwrap(PredicateContext ctx);

}
