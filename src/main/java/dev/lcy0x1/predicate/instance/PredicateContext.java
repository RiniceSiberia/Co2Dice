package dev.lcy0x1.predicate.instance;

import dev.lcy0x1.predicate.api.instance.IBiFunction;
import dev.lcy0x1.predicate.api.instance.IFunction;
import dev.lcy0x1.predicate.syntax.operation.instance.api.IOperationInstanceDouble;
import dev.lcy0x1.predicate.syntax.operation.instance.api.IOperationInstanceSingle;
import dev.lcy0x1.predicate.syntax.type.OperandType;

import java.util.function.Function;

public interface PredicateContext {

	<T> IValueInstance<T> buildInstance(OperandType<T> tOperandType, Function<PredicateContext, T> val);

	<B, A, O> IBiFunction<O, A, B> buildChildren(IOperationInstanceDouble<O, A, B> v);

	<O, I> IFunction<O,I> buildChildren(IOperationInstanceSingle<O,I> v);

}
