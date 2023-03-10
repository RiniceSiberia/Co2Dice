package dev.lcy0x1.predicate.instance;

import dev.lcy0x1.predicate.syntax.type.OperandType;

public class ValueInstanceConstant<T> implements IValueInstance<T> {

	private final OperandType<T> type;

	private final T value;

	public ValueInstanceConstant(OperandType<T> type, T value) {
		this.type = type;
		this.value = value;
	}

	@Override
	public OperandType<T> getType() {
		return type;
	}

	@Override
	public T getVal(PredicateContext ctx) {
		return value;
	}

}
