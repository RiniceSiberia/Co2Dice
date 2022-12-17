package dev.lcy0x1.predicate.instance;

import dev.lcy0x1.predicate.syntax.OperandType;

import java.util.List;
import java.util.function.Function;

public interface PredicateContext {

	<T> IOperandInstance<T> buildInstance(OperandType<T> tOperandType, Function<PredicateContext, T> val);

	IPredicate buildChildren(List<IOperandInstance<ITarget>> a, IOperandInstance<Boolean> b);
}
