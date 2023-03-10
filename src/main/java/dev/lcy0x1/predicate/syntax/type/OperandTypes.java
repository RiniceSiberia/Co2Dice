package dev.lcy0x1.predicate.syntax.type;

import com.google.gson.JsonArray;
import com.google.gson.JsonPrimitive;
import dev.lcy0x1.predicate.api.instance.IBiFunction;
import dev.lcy0x1.predicate.api.instance.IEntity;
import dev.lcy0x1.predicate.api.instance.IFunction;
import dev.lcy0x1.predicate.instance.IAttributeType;
import dev.lcy0x1.predicate.instance.ValueInstanceConstant;
import dev.lcy0x1.predicate.instance.ValueInstanceList;
import dev.lcy0x1.util.ListHelper;

import java.util.List;

public class OperandTypes {
	//这个类记录可操作的数据类型，基本类如bool,number,name,attribute,entity

	public static final OperandType<Boolean> BOOL = new OperandType<>("bool", JsonPrimitive::new,
			(t, ctx, e) -> new ValueInstanceConstant<>(t, e.getAsBoolean()));
	public static final OperandType<Integer> NUMBER = new OperandType<>("number", JsonPrimitive::new,
			(t, ctx, e) -> new ValueInstanceConstant<>(t, e.getAsInt()));
	public static final OperandType<String> NAME = new OperandType<>("name", JsonPrimitive::new,
			(t, ctx, e) -> new ValueInstanceConstant<>(t, e.getAsString()));
	public static final OperandType<IAttributeType<?>> ATTRIBUTE = new OperandType<>("attribute",
			e -> new JsonPrimitive(e.name()),
			(t, ctx, e) -> new ValueInstanceConstant<>(t, ctx.getEnv().parseAttributeType(e.getAsString())));
	public static final OperandType<IEntity> ENTITY = new OperandType<>("entity", null, null);

	public static <E> OperandType<List<E>> listType(OperandType<E> element) {
		return new GenericType1<>("list", element,
				list -> ListHelper.collect(new JsonArray(), list, (a, e) -> a.add(element.encode(e))),
				(t, ctx, elem) -> {
					if (!elem.isJsonArray()) {
						throw new RuntimeException(elem.toString() + " is not a list");
					}
					var arr = elem.getAsJsonArray();
					return new ValueInstanceList<>(t);//TODO
				});
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
