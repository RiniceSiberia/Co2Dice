package dev.lcy0x1.predicate.syntax.operation.instance.api;

import dev.lcy0x1.predicate.api.instance.IFunction;
import dev.lcy0x1.predicate.syntax.operation.action.IOperationDouble;
import dev.lcy0x1.predicate.syntax.operation.action.IOperationList;
import dev.lcy0x1.predicate.syntax.operation.action.IOperationSingle;
import dev.lcy0x1.predicate.syntax.operation.instance.simple.SimpleOperationInstanceDouble;
import dev.lcy0x1.predicate.syntax.operation.instance.simple.SimpleOperationInstanceSingle;
import dev.lcy0x1.predicate.syntax.param.type.IParam;
import dev.lcy0x1.predicate.syntax.param.type.ParamList;
import dev.lcy0x1.predicate.syntax.param.type.ParamValue;
import dev.lcy0x1.predicate.syntax.param.type.ParameterSet;
import dev.lcy0x1.predicate.syntax.type.OperandType;
import dev.lcy0x1.util.type.Either;

import java.util.List;
import java.util.function.BiFunction;

public interface IOperationFactory<T extends IOperationInstance> {

	static <O, I> IOperationFactory<IOperationInstanceSingle<O, I>> simpleS(
			OperandType<O> output,
			ParamValue<I> param,
			IOperationSingle<O, I> func) {
		return SimpleOperationInstanceSingle.of(output, param, func);
	}
	//这是操作符有一个传参

	static <O, I> IOperationFactory<IOperationInstanceSingle<O, List<I>>> simpleL(
			OperandType<O> output,
			ParamList<I> param,
			IOperationList<O, I> func) {
		return SimpleOperationInstanceSingle.of(output, param, func);
	}
	//这个操作符有一个传参，但是是对List操作

	static <O, A, B> IOperationFactory<IOperationInstanceDouble<O, A, B>> simpleSS(
			OperandType<O> output,
			 ParamValue<A> first,
			 ParamValue<B> second,
			 IOperationDouble<O, A, B> func
	) {
		return SimpleOperationInstanceDouble.of(output, first, second, func);
	}
	//这个操作符需要两个参数，类不同，A和B

	T parse(ParameterSet set);

	Either<OperandType<IFunction<?, ?>>, OperandType<BiFunction<?, ?, ?>>> toFunctionType();

	List<IParam> getParams();

}
