package dev.lcy0x1.predicate.syntax.type;

import java.util.List;

public class GenericType2<T, A, B> extends OperandType<T> implements GenericType {

	private final OperandType<A> a;
	private final OperandType<B> b;

	public GenericType2(String name, OperandType<A> a, OperandType<B> b) {
		super(name, null);
		this.a = a;
		this.b = b;
	}

	public List<OperandType<?>> getGenericTypes() {
		return List.of(a, b);
	}

}
