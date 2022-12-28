package dev.lcy0x1.predicate.syntax.operation.instance.simple;

import dev.lcy0x1.predicate.api.instance.IFunction;
import dev.lcy0x1.predicate.syntax.operation.action.IOperationDouble;
import dev.lcy0x1.predicate.syntax.operation.instance.api.IOperationFactory;
import dev.lcy0x1.predicate.syntax.operation.instance.api.IOperationInstanceDouble;
import dev.lcy0x1.predicate.syntax.param.instance.IParamInstance;
import dev.lcy0x1.predicate.syntax.param.type.ParamValue;
import dev.lcy0x1.predicate.syntax.param.type.ParameterSet;
import dev.lcy0x1.predicate.syntax.type.OperandType;
import dev.lcy0x1.predicate.syntax.type.OperandTypes;
import dev.lcy0x1.util.CastHelper;
import dev.lcy0x1.util.type.Either;

import java.util.List;
import java.util.function.BiFunction;

public class SimpleOperationInstanceDouble<O, A, B>
		extends SimpleOperationInstance<O>
		implements IOperationInstanceDouble<O, A, B>,
		IOperationFactory<IOperationInstanceDouble<O, A, B>> {

	public static <O, A, B> SimpleOperationInstanceDouble<O, A, B>
	of(OperandType<O> output, ParamValue<A> a, ParamValue<B> b, IOperationDouble<O, A, B> func) {
		return new SimpleOperationInstanceDouble<>(output, a, b, func);
	}

	private final IParamInstance<A> a;
	private final IParamInstance<B> b;
	private final IOperationDouble<O, A, B> operation;

	private SimpleOperationInstanceDouble(OperandType<O> output, IParamInstance<A> a, IParamInstance<B> b, IOperationDouble<O, A, B> func) {
		super(output, List.of(a, b));
		this.operation = func;
		this.a = a;
		this.b = b;
	}

	@Override
	public void verify(ParameterSet set) {
		//TODO
	}

	@Override
	public IOperationInstanceDouble<O, A, B> parse(ParameterSet set) {
		verify(set);
		return this;
	}

	@Override
	public OperandType<A> getFirstType() {
		return a.type();
	}

	@Override
	public OperandType<B> getSecondType() {
		return b.type();
	}

	@Override
	public Either<OperandType<IFunction<?, ?>>, OperandType<BiFunction<?, ?, ?>>> toFunctionType() {
		return CastHelper.unsafeCast(Either.ofRight(OperandTypes.biFunctionType(getOutputType(), getFirstType(),getSecondType())));
	}

}
