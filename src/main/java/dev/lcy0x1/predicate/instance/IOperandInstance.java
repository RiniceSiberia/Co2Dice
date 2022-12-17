package dev.lcy0x1.predicate.instance;

import dev.lcy0x1.predicate.syntax.OperandType;

public interface IOperandInstance<T> {

	public OperandType<T> getType();

	public T getVal(PredicateContext ctx);

}
