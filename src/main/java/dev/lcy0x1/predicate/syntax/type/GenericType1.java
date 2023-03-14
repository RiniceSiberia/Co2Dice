package dev.lcy0x1.predicate.syntax.type;

import com.google.gson.JsonElement;

import java.util.List;
import java.util.function.Function;

public class GenericType1<T, A> extends OperandType<T> implements GenericType {

	private final OperandType<A> generic;

	public GenericType1(String name, OperandType<A> element,
						Function<T, JsonElement> encoder,
						IDecoder<T> decoder) {
		super(name, encoder, decoder);
		generic = element;
	}

	@Override
	public List<OperandType<?>> getGenericTypes() {
		return List.of(generic);
	}

}
