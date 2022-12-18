package dev.lcy0x1.predicate.instance;

import dev.lcy0x1.predicate.syntax.type.OperandType;

import java.util.List;

public class ValueInstanceList<T> implements IValueInstanceList<T> {

	private final OperandType<List<T>> type;

	public ValueInstanceList(OperandType<List<T>> type) {
		this.type = type;
	}

	@Override
	public List<T> getVal(PredicateContext ctx) {
		return null;
	}

	@Override
	public OperandType<List<T>> getType() {
		return type;
	}

	@Override
	public List<ValueInstanceConstant<T>> unwrap(PredicateContext ctx) {
		return null;
	}
}
