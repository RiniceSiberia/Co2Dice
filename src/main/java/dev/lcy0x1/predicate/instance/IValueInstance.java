package dev.lcy0x1.predicate.instance;

import dev.lcy0x1.predicate.syntax.type.OperandType;

public interface IValueInstance<T> {

	OperandType<T> getType();

	T getVal(PredicateContext ctx);

}
