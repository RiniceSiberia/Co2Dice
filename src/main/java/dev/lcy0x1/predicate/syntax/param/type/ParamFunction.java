package dev.lcy0x1.predicate.syntax.param.type;

import dev.lcy0x1.predicate.instance.IValueInstance;
import dev.lcy0x1.predicate.instance.IValueInstanceName;
import dev.lcy0x1.predicate.syntax.operation.Operator;
import dev.lcy0x1.predicate.syntax.operation.instance.api.IOperationInstance;
import dev.lcy0x1.predicate.syntax.param.instance.IParamInstance;
import dev.lcy0x1.predicate.syntax.param.instance.ParamBiFunctionInstance;
import dev.lcy0x1.predicate.syntax.param.instance.ParamFunctionInstance;
import dev.lcy0x1.predicate.syntax.type.OperandTypes;
import dev.lcy0x1.util.CastHelper;

public class ParamFunction implements IParam {

	private final String name;

	public ParamFunction(String name) {
		this.name = name;
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public IParamInstance<?> build(IValueInstance<?> val) {
		if (val.getType() != OperandTypes.OPERATOR) {
			throw new RuntimeException(name + "requires operation, got " + val.getType());
		}
		return typeSafeBuild(CastHelper.unsafeCast(val));
	}

	@SuppressWarnings({"rawtypes", "unsafe", "unchecked"})
	private <T extends IOperationInstance> IParamInstance typeSafeBuild(IValueInstance<Operator<T>> t) {
		Operator<T> op = IValueInstanceName.asEnum(t).preview();
		return op.toFunctionType().map(e -> new ParamFunctionInstance(e, this), e -> new ParamBiFunctionInstance(e, this));
	}

}
