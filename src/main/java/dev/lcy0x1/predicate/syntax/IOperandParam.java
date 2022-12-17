package dev.lcy0x1.predicate.syntax;

public interface IOperandParam<T> {

	static <T> _OperandParamSingleton<T> getSingleton(OperandType<T> type, String name) {
		return new _OperandParamSingleton<>(type, name);
	}

	static <T> _OperandParamList<T> getList(OperandType<T> type, String name, int min) {
		return new _OperandParamList<>(type, name, min);
	}

	OperandType<T> type();

}
