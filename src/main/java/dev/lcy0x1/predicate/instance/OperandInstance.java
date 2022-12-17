package dev.lcy0x1.predicate.instance;

import dev.lcy0x1.predicate.syntax.OperandType;

public class OperandInstance<T> {

	private final OperandType<T> type;

	private T value;

	public OperandInstance(OperandType<T> type, T value) {
		this.type = type;
		this.value = value;
	}

	public OperandType<T> getType() {
		return type;
	}

	public T getVal(PredicateContext ctx) {
		return value;
	}

}
