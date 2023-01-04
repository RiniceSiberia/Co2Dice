package dev.lcy0x1.predicate.syntax.param.type;

import com.google.gson.JsonElement;
import dev.lcy0x1.predicate.instance.IValueInstance;
import dev.lcy0x1.predicate.instance.IValueInstanceName;
import dev.lcy0x1.predicate.instance.PredicateContext;
import dev.lcy0x1.predicate.syntax.operation.Operator;
import dev.lcy0x1.predicate.syntax.operation.instance.api.IOperationInstance;
import dev.lcy0x1.predicate.syntax.param.instance.IParamInstance;
import dev.lcy0x1.predicate.syntax.param.instance.ParamBiFunctionInstance;
import dev.lcy0x1.predicate.syntax.param.instance.ParamFunctionInstance;
import dev.lcy0x1.predicate.syntax.type.OperandTypes;
import dev.lcy0x1.predicate.util.CastHelper;

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
	public IValueInstance<?> decode(PredicateContext ctx, JsonElement elem) {
		return null;
	}

	@Override
	public IParamInstance<?> build(IValueInstance<?> val) {
		return typeSafeBuild(CastHelper.unsafeCast(val));
	}

	@SuppressWarnings({"rawtypes", "unsafe", "unchecked"})
	private <T extends IOperationInstance> IParamInstance typeSafeBuild(IValueInstance<Operator<T>> t) {
		Operator<T> op = IValueInstanceName.asEnum(t).preview();
		return op.toFunctionType().map(e -> new ParamFunctionInstance(e, this), e -> new ParamBiFunctionInstance(e, this));
	}

}
