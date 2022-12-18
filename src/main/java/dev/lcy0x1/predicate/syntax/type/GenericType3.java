package dev.lcy0x1.predicate.syntax.type;

import java.util.List;

public class GenericType3<T, A, B, C> extends OperandType<T> implements GenericType {

	private final OperandType<A> a;
	private final OperandType<B> b;
	private final OperandType<C> c;

	public GenericType3(String name, OperandType<A> a, OperandType<B> b, OperandType<C> c) {
		super(name, null);
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public List<OperandType<?>> getGenericTypes() {
		return List.of(a, b, c);
	}

}
