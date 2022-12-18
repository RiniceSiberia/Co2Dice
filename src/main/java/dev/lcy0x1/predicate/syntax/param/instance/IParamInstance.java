package dev.lcy0x1.predicate.syntax.param.instance;

import dev.lcy0x1.predicate.syntax.param.type.IParam;
import dev.lcy0x1.predicate.syntax.type.OperandType;

public interface IParamInstance<T> {

	OperandType<T> type();

	IParam param();

	default String name() {
		return param().name();
	}

}
