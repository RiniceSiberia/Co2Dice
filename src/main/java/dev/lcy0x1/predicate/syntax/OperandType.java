package dev.lcy0x1.predicate.syntax;

import dev.lcy0x1.predicate.instance.IOperandInstance;
import dev.lcy0x1.predicate.instance.IPredicate;
import dev.lcy0x1.predicate.instance.ITarget;
import dev.lcy0x1.predicate.instance.PredicateContext;

import java.util.List;
import java.util.function.Function;

public class OperandType<T> {

	public static final OperandType<Boolean> BOOL = new OperandType<>("bool");
	public static final OperandType<Integer> NUMBER = new OperandType<>("number");
	public static final OperandType<String> NAME = new OperandType<>("name");
	public static final OperandType<String> ATTRIBUTE_TYPE = new OperandType<>("attribute_type");
	public static final OperandType<ITarget> TARGET = new OperandType<>("target");
	public static final OperandType<List<?>> LIST = new OperandType<>("list");
	public static final OperandType<IPredicate> PREDICATE = new OperandType<>("predicate");

	private final String name;

	public OperandType(String name) {
		this.name = name;
	}

	public IOperandInstance<T> parse(PredicateContext ctx, Function<PredicateContext, T> val) {
		return ctx.buildInstance(this, val);
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof OperandType op && name.equals(op.name);
	}

}
