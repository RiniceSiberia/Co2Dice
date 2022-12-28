package dev.lcy0x1.predicate.syntax.operation.instance.simple;

import dev.lcy0x1.predicate.api.instance.IFunction;
import dev.lcy0x1.predicate.syntax.operation.action.IOperationList;
import dev.lcy0x1.predicate.syntax.operation.action.IOperationSingle;
import dev.lcy0x1.predicate.syntax.operation.instance.api.IOperationFactory;
import dev.lcy0x1.predicate.syntax.operation.instance.api.IOperationInstanceSingle;
import dev.lcy0x1.predicate.syntax.param.instance.IParamInstance;
import dev.lcy0x1.predicate.syntax.param.type.ParamList;
import dev.lcy0x1.predicate.syntax.param.type.ParamValue;
import dev.lcy0x1.predicate.syntax.param.type.ParameterSet;
import dev.lcy0x1.predicate.syntax.type.OperandType;
import dev.lcy0x1.predicate.syntax.type.OperandTypes;
import dev.lcy0x1.util.CastHelper;
import dev.lcy0x1.util.type.Either;

import java.util.List;
import java.util.function.BiFunction;

public class SimpleOperationInstanceSingle<O, I>
		extends SimpleOperationInstance<O>
		implements IOperationInstanceSingle<O, I>,
		IOperationFactory<IOperationInstanceSingle<O, I>> {

	public static <O, I> SimpleOperationInstanceSingle<O, I>
	of(OperandType<O> output, ParamValue<I> param, IOperationSingle<O, I> func) {
		return new SimpleOperationInstanceSingle<>(output, param, func);
	}

	public static <O, I> SimpleOperationInstanceSingle<O, List<I>>
	of(OperandType<O> output, ParamList<I> param, IOperationList<O, I> func) {
		return new SimpleOperationInstanceSingle<>(output, param, func);
	}

	private final IOperationSingle<O, I> operation;
	private final IParamInstance<I> param;

	private SimpleOperationInstanceSingle(OperandType<O> output, IParamInstance<I> param, IOperationSingle<O, I> func) {
		super(output, List.of(param));
		this.param = param;
		this.operation = func;
	}

	@Override
	public void verify(ParameterSet set) {
		//TODO
	}

	@Override
	public OperandType<I> getParameterType() {
		return param.type();
	}

	@Override
	public IOperationInstanceSingle<O, I> parse(ParameterSet set) {
		verify(set);
		return this;
	}

	@Override
	public Either<OperandType<IFunction<?, ?>>, OperandType<BiFunction<?, ?, ?>>> toFunctionType() {
		return CastHelper.unsafeCast(Either.ofLeft(OperandTypes.functionType(getOutputType(), getParameterType())));
	}

}
