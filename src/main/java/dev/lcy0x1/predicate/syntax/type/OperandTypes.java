package dev.lcy0x1.predicate.syntax.type;

import com.google.gson.JsonPrimitive;
import dev.lcy0x1.predicate.api.instance.IBiFunction;
import dev.lcy0x1.predicate.api.instance.IEntity;
import dev.lcy0x1.predicate.api.instance.IFunction;
import dev.lcy0x1.predicate.syntax.operation.Operator;

import java.util.List;

public class OperandTypes {
	public static final OperandType<Boolean> BOOL = new OperandType<>("bool", JsonPrimitive::new);
	public static final OperandType<Integer> NUMBER = new OperandType<>("number", JsonPrimitive::new);
	public static final OperandType<String> NAME = new OperandType<>("name", JsonPrimitive::new);
	public static final OperandType<IEntity> ENTITY = new OperandType<>("entity", null);
	public static final OperandType<Operator<?>> OPERATOR = new EnumType<>("operator");

	public static <E> OperandType<List<E>> listType(OperandType<E> element) {
		return new GenericType1<>("list", element, null);//TODO
	}

	public static <O, I> OperandType<IFunction<O, I>>
	functionType(OperandType<O> output, OperandType<I> input) {
		return new GenericType2<>("function", output, input);
	}

	public static <O, A, B> OperandType<IBiFunction<O, A, B>>
	biFunctionType(OperandType<O> output, OperandType<A> a, OperandType<B> b) {
		return new GenericType3<>("bifunction", output, a, b);
	}
}
