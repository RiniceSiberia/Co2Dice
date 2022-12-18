package dev.lcy0x1.predicate.syntax.type;

import com.google.gson.JsonElement;

import java.util.List;
import java.util.function.Function;

public class GenericType1<T, A> extends OperandType<T> implements GenericType {

	private final OperandType<A> generic;

	public GenericType1(String name, OperandType<A> element, Function<T, JsonElement> encoder) {
		super(name, encoder);
		generic = element;
	}



	public List<OperandType<?>> getGenericTypes() {
		return List.of(generic);
	}

}
