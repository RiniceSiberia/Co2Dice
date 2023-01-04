package dev.lcy0x1.predicate.syntax.type;

import com.google.gson.JsonPrimitive;

public class EnumType<T extends NamedEnum> extends OperandType<T> {

	public EnumType(String name) {
		super(name, e -> new JsonPrimitive(e.getName()), null);
	}

}
