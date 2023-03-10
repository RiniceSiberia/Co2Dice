package dev.lcy0x1.predicate.datagen;

import dev.lcy0x1.predicate.syntax.operation.Operator;
import dev.lcy0x1.predicate.syntax.type.OperandType;
import dev.lcy0x1.predicate.syntax.type.OperandTypes;

public class Test {

	public static void main(String[] args) {
		Operator.ADD.build(OperandTypes.NUMBER.constantToken(1), OperandTypes.NUMBER.constantToken(2));

	}

}
