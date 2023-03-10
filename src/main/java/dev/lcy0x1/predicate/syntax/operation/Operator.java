package dev.lcy0x1.predicate.syntax.operation;

import dev.lcy0x1.predicate.api.instance.IFunction;
import dev.lcy0x1.predicate.syntax.operation.instance.api.IOperationFactory;
import dev.lcy0x1.predicate.syntax.operation.instance.api.IOperationInstance;
import dev.lcy0x1.predicate.syntax.operation.instance.api.IOperationInstanceDouble;
import dev.lcy0x1.predicate.syntax.operation.instance.api.IOperationInstanceSingle;
import dev.lcy0x1.predicate.syntax.param.type.IParam;
import dev.lcy0x1.predicate.syntax.param.type.ParameterSet;
import dev.lcy0x1.predicate.syntax.type.NamedEnum;
import dev.lcy0x1.predicate.syntax.type.OperandType;
import dev.lcy0x1.predicate.syntax.type.OperandTypes;
import dev.lcy0x1.util.ListHelper;
import dev.lcy0x1.util.type.Either;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class Operator<T extends IOperationInstance> implements NamedEnum {

	private final IOperationFactory<T> factory;
	private final String name;

	//operator(操作符)有两个属性:name(名称)，factory(一个函数)

	private Operator(String name, IOperationFactory<T> factory) {
		this.name = name;
		this.factory = factory;
		REGISTRY.put(name, this);
	}
	//构造时会注册

	public static Operator<?> getType(String type) {
		if (!REGISTRY.containsKey(type)) {
			throw new RuntimeException("type " + type + " not found");
		}
		return REGISTRY.get(type);
	}
	//通过类型来获取注册器中的操作符

	@Override
	public String getName() {
		return name;
	}

	public T getOperator(ParameterSet set) {
		return factory.parse(set);
	}

	public Either<OperandType<IFunction<?, ?>>, OperandType<BiFunction<?, ?, ?>>> toFunctionType() {
		return factory.toFunctionType();
	}

	public List<IParam> getParams() {
		return factory.getParams();
	}

	private static final Map<String, Operator<?>> REGISTRY = new HashMap<>();

	public static final Operator<IOperationInstanceSingle<Boolean, Boolean>> NOT =
			new Operator<>("NOT", IOperationFactory.simpleS(OperandTypes.BOOL,
					IParam.getSingleton(OperandTypes.BOOL, "val"),
					(ctx, e) -> OperandTypes.BOOL.parse(ctx, c -> !e.getVal(c))
			));
	//

	public static final Operator<IOperationInstanceSingle<Boolean, List<Boolean>>> AND =
			new Operator<>("AND", IOperationFactory.simpleL(OperandTypes.BOOL,
					IParam.getList(OperandTypes.BOOL, "val", 2),
					(ctx, e) -> OperandTypes.BOOL.parse(ctx,
							c -> ListHelper.reduce(true, e.unwrap(c), (u, x) -> u && x.getVal(c)))
			));
	//以and举例:他的factory是一个函数，这个函数的参数是一个ParameterSet，返回值是一个IOperationInstanceSingle

	public static final Operator<IOperationInstanceSingle<Boolean, List<Boolean>>> OR =
			new Operator<>("OR", IOperationFactory.simpleL(OperandTypes.BOOL,
					IParam.getList(OperandTypes.BOOL, "val", 2),
					(ctx, e) -> OperandTypes.BOOL.parse(ctx,
							c -> ListHelper.reduce(false, e.unwrap(c), (u, x) -> u || x.getVal(c)))
			));

	public static final Operator<IOperationInstanceSingle<Boolean, List<Boolean>>> XOR =
			new Operator<>("XOR", IOperationFactory.simpleL(OperandTypes.BOOL,
					IParam.getList(OperandTypes.BOOL, "val", 2),
					(ctx, e) -> OperandTypes.BOOL.parse(ctx,
							c -> ListHelper.reduce(false, e.unwrap(c), (u, x) -> u ^ x.getVal(c)))
			));

	public static final Operator<IOperationInstanceSingle<Boolean, List<Integer>>> EQ =
			new Operator<>("EQ", IOperationFactory.simpleL(OperandTypes.BOOL,
					IParam.getList(OperandTypes.NUMBER, "val", 2),
					(ctx, e) -> OperandTypes.BOOL.parse(ctx,
							c -> ListHelper.eq(e.getVal(c)))
			));

	public static final Operator<IOperationInstanceSingle<Boolean, List<Integer>>> NEQ =
			new Operator<>("NEQ", IOperationFactory.simpleL(OperandTypes.BOOL,
					IParam.getList(OperandTypes.NUMBER, "val", 2),
					(ctx, e) -> OperandTypes.BOOL.parse(ctx,
							c -> ListHelper.neq(e.getVal(c)))
			));

	public static final Operator<IOperationInstanceDouble<Boolean, Integer, Integer>> LE =
			new Operator<>("LE", IOperationFactory.simpleSS(OperandTypes.BOOL,
					IParam.getSingleton(OperandTypes.NUMBER, "left"),
					IParam.getSingleton(OperandTypes.NUMBER, "right"),
					(ctx, a, b) -> OperandTypes.BOOL.parse(ctx, c -> a.getVal(c) < b.getVal(c))
			));

	public static final Operator<IOperationInstanceDouble<Boolean, Integer, Integer>> LEQ =
			new Operator<>("LEQ", IOperationFactory.simpleSS(OperandTypes.BOOL,
					IParam.getSingleton(OperandTypes.NUMBER, "left"),
					IParam.getSingleton(OperandTypes.NUMBER, "right"),
					(ctx, a, b) -> OperandTypes.BOOL.parse(ctx, c -> a.getVal(c) <= b.getVal(c))
			));

	public static final Operator<IOperationInstanceDouble<Boolean, Integer, Integer>> GE =
			new Operator<>("GE", IOperationFactory.simpleSS(OperandTypes.BOOL,
					IParam.getSingleton(OperandTypes.NUMBER, "left"),
					IParam.getSingleton(OperandTypes.NUMBER, "right"),
					(ctx, a, b) -> OperandTypes.BOOL.parse(ctx, c -> a.getVal(c) > b.getVal(c))
			));

	public static final Operator<IOperationInstanceDouble<Boolean, Integer, Integer>> GEQ =
			new Operator<>("GEQ", IOperationFactory.simpleSS(OperandTypes.BOOL,
					IParam.getSingleton(OperandTypes.NUMBER, "left"),
					IParam.getSingleton(OperandTypes.NUMBER, "right"),
					(ctx, a, b) -> OperandTypes.BOOL.parse(ctx, c -> a.getVal(c) >= b.getVal(c))
			));

	public static final Operator<IOperationInstanceSingle<Integer, List<Integer>>> ADD =
			new Operator<>("ADD", IOperationFactory.simpleL(OperandTypes.NUMBER,
					IParam.getList(OperandTypes.NUMBER, "val", 2),
					(ctx, e) -> OperandTypes.NUMBER.parse(ctx,
							c -> ListHelper.reduce(0, e.unwrap(c), (u, x) -> u + x.getVal(c)))
			));

	public static final Operator<IOperationInstanceDouble<Integer, Integer, Integer>> SUBTRACT =
			new Operator<>("SUBTRACT", IOperationFactory.simpleSS(OperandTypes.NUMBER,
					IParam.getSingleton(OperandTypes.NUMBER, "left"),
					IParam.getSingleton(OperandTypes.NUMBER, "right"),
					(ctx, a, b) -> OperandTypes.NUMBER.parse(ctx, c -> a.getVal(c) - b.getVal(c))
			));

	public static final Operator<IOperationInstanceSingle<Integer, List<Integer>>> MULTIPLY =
			new Operator<>("MULTIPLY", IOperationFactory.simpleL(OperandTypes.NUMBER,
					IParam.getList(OperandTypes.NUMBER, "val", 2),
					(ctx, e) -> OperandTypes.NUMBER.parse(ctx,
							c -> ListHelper.reduce(0, e.unwrap(c), (u, x) -> u * x.getVal(c)))
			));

}
