package dev.lcy0x1.predicate.syntax.type;

import com.google.gson.JsonElement;
import dev.lcy0x1.predicate.instance.IValueInstance;
import dev.lcy0x1.predicate.instance.PredicateContext;

public interface IDecoder<T> {

	IValueInstance<T> decode(OperandType<T> type, PredicateContext ctx, JsonElement elem);

}
