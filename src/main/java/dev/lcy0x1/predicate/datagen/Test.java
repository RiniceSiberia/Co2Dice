package dev.lcy0x1.predicate.datagen;

import dev.lcy0x1.predicate.syntax.operation.Operator;
import dev.lcy0x1.predicate.syntax.type.OperandTypes;

/**
 * @author user
 */
public class Test {

	public static void main(String[] args) {
		Operator.Companion.getADD().build(OperandTypes.INSTANCE.getNUMBER().constantToken(1), OperandTypes.INSTANCE.getNUMBER().constantToken(2));

	}

}
