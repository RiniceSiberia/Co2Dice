package dev.lcy0x1.predicate.syntax.type;

import com.google.gson.JsonElement;
import dev.lcy0x1.predicate.datagen.encoder.ConstantToken;
import dev.lcy0x1.predicate.datagen.encoder.IValueToken;
import dev.lcy0x1.predicate.instance.IValueInstance;
import dev.lcy0x1.predicate.instance.PredicateContext;
import dev.lcy0x1.predicate.instance.ValueInstanceConstant;

import java.util.function.Function;

public class OperandType<T> {

	private final String name;
	private final Function<T, JsonElement> encoder;
	private final IDecoder<T> decoder;

	public OperandType(String name, Function<T, JsonElement> encoder, IDecoder<T> decoder) {
		this.name = name;
		this.encoder = encoder;
		this.decoder = decoder;
		if (encoder == null ^ decoder == null) {
			throw new RuntimeException("encoder and decoder must be in pair");
		}
	}

	public IValueInstance<T> parse(PredicateContext ctx, Function<PredicateContext, T> val) {
		return ctx.buildInstance(this, val);
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof OperandType op && name.equals(op.name);
	}

	public IValueToken<T> constantToken(T val) {
		if (encoder == null)
			throw new RuntimeException(name + " does not support constant");
		return new ConstantToken<>(this, val);
	}

	public JsonElement encode(T value) {
		if (encoder == null)
			throw new RuntimeException(name + " does not support constant");
		return encoder.apply(value);
	}

	public IValueInstance<?> decode(PredicateContext ctx, JsonElement elem) {
		if (decoder == null)
			throw new RuntimeException(name + " does not support constant");
		if (elem.isJsonPrimitive()) {
			var prim = elem.getAsJsonPrimitive();
			if (prim.isString()) {
				var str = prim.getAsString();
				if (str.startsWith("$")) {
					return ctx.getField(str);
				}
			}
		}
		return decoder.decode(this, ctx, elem);
	}
}
