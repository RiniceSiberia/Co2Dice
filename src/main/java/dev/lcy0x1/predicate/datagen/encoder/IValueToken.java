package dev.lcy0x1.predicate.datagen.encoder;

import com.google.gson.JsonElement;
import dev.lcy0x1.predicate.syntax.type.OperandType;

public interface IValueToken<T> {

	OperandType<T> getType();

	JsonElement encode();

}
