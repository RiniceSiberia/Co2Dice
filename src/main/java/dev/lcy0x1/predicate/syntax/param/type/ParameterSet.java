package dev.lcy0x1.predicate.syntax.param.type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import dev.lcy0x1.predicate.instance.IValueInstance;
import dev.lcy0x1.predicate.instance.PredicateContext;
import dev.lcy0x1.predicate.syntax.operation.Operator;
import dev.lcy0x1.predicate.syntax.param.instance.IParamInstance;
import dev.lcy0x1.util.CastHelper;

import java.util.HashMap;
import java.util.Map;

public class ParameterSet {

	private final Map<String, IValueInstance<?>> map = new HashMap<>();

	public ParameterSet(PredicateContext ctx, JsonObject obj) {
		String type = obj.get("type").getAsString();
		Operator<?> op = Operator.getType(type);
		for (IParam param : op.getParams()) {
			if (!obj.has(param.name())) {
				throw new RuntimeException(param.name() + " not found, expected for " + type);
			}
			JsonElement elem = obj.get(param.name());
			var val = param.decode(ctx, elem);
			map.put(param.name(), val);
		}
	}

	public <T> IValueInstance<T> getValue(IParamInstance<T> param) {
		if (!map.containsKey(param.name())) {
			throw new RuntimeException(param.name() + " not found among " + map.keySet());
		}
		var ans = map.get(param.name());
		if (ans.getType() != param.type()) {
			throw new RuntimeException(param.name() + " expects type " + param.type() + " but gets " + ans.getType());
		}
		return CastHelper.unsafeCast(ans);
	}

}
