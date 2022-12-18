package dev.lcy0x1.predicate.syntax.type;

import com.google.gson.JsonElement;
import dev.lcy0x1.predicate.datagen.encoder.ConstantToken;
import dev.lcy0x1.predicate.datagen.encoder.IValueToken;
import dev.lcy0x1.predicate.instance.IValueInstance;
import dev.lcy0x1.predicate.instance.PredicateContext;

import java.util.function.Function;

public class OperandType<T> {

	private final String name;
	private final Function<T, JsonElement> encoder;

	public OperandType(String name, Function<T, JsonElement> encoder) {
		this.name = name;
		this.encoder = encoder;
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
}
