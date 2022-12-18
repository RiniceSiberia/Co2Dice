package dev.lcy0x1.predicate.syntax.param.type;

import dev.lcy0x1.predicate.instance.IValueInstance;
import dev.lcy0x1.predicate.syntax.param.instance.IParamInstance;
import dev.lcy0x1.predicate.syntax.type.OperandType;
import dev.lcy0x1.predicate.syntax.type.OperandTypes;

public interface IParam {

	static <T> ParamValue<T> getSingleton(OperandType<T> type, String name) {
		return new ParamValue<>(type, name);
	}

	static <T> ParamList<T> getList(OperandType<T> type, String name, int min) {
		return new ParamList<>(OperandTypes.listType(type), name, min);
	}

	String name();

	IParamInstance<?> build(IValueInstance<?> val);

}
