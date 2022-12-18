package dev.lcy0x1.predicate.instance;

import dev.lcy0x1.predicate.syntax.type.OperandType;

public interface IAttributeType<T> {

	OperandType<T> getType();

	/**
	 * starts with #
	 */
	String name();
}
