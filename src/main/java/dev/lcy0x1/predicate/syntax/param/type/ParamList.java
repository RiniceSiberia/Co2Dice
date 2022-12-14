package dev.lcy0x1.predicate.syntax.param.type;

import com.google.gson.JsonElement;
import dev.lcy0x1.predicate.instance.IValueInstance;
import dev.lcy0x1.predicate.instance.PredicateContext;
import dev.lcy0x1.predicate.syntax.param.instance.IParamInstance;
import dev.lcy0x1.predicate.syntax.type.OperandType;

import java.util.List;

public record ParamList<T>(OperandType<List<T>> type, String name, int min)
		implements IParam, IParamInstance<List<T>> {

	@Override
	public IValueInstance<?> decode(PredicateContext ctx, JsonElement elem) {
		return type().decode(ctx, elem);
	}

	@Override
	public IParamInstance<List<T>> build(IValueInstance<?> val) {
		return this;
	}

	@Override
	public IParam param() {
		return this;
	}

}
