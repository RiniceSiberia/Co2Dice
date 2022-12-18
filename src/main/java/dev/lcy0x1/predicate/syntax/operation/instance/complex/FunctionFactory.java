package dev.lcy0x1.predicate.syntax.operation.instance.complex;

import dev.lcy0x1.predicate.api.instance.IFunction;
import dev.lcy0x1.predicate.syntax.operation.instance.api.IOperationFactory;
import dev.lcy0x1.predicate.syntax.param.type.IParam;
import dev.lcy0x1.predicate.syntax.param.type.ParameterSet;
import dev.lcy0x1.predicate.syntax.type.OperandType;
import dev.lcy0x1.predicate.util.type.Either;

import java.util.List;
import java.util.function.BiFunction;

public class FunctionFactory implements IOperationFactory<FunctionOperationInstance> {

	@Override
	public FunctionOperationInstance parse(ParameterSet set) {
		return null;//TODO
	}

	@Override
	public Either<OperandType<IFunction<?, ?>>, OperandType<BiFunction<?, ?, ?>>> toFunctionType() {
		throw new RuntimeException("FUNCTION cannot be converted to function");
	}

	@Override
	public List<IParam> getParams() {
		return List.of();//TODO
	}
}
