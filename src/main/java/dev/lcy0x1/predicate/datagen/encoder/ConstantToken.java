package dev.lcy0x1.predicate.datagen.encoder;

import com.google.gson.JsonElement;
import dev.lcy0x1.predicate.syntax.type.OperandType;

public class ConstantToken<T> implements IValueToken<T> {

	private final OperandType<T> type;
	private final T value;

	public ConstantToken(OperandType<T> type, T value) {
		this.type = type;
		this.value = value;
	}

	@Override
	public OperandType<T> getType() {
		return type;
	}

	@Override
	public JsonElement encode() {
		return type.encode(value);
	}
}
