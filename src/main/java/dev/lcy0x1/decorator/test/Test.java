package dev.lcy0x1.decorator.test;

import dev.lcy0x1.decorator.env.AttributeType;
import dev.lcy0x1.decorator.env.Card;
import dev.lcy0x1.decorator.env.Duel;
import dev.lcy0x1.decorator.handler.DecoratorRegistry;
import dev.lcy0x1.decorator.implementation.SimplePermanentAddValueDecorator;
import dev.lcy0x1.decorator.implementation.SimplePermanentMultiplyValueDecorator;
import dev.lcy0x1.decorator.implementation.SimplePermanentSwitchStatDecorator;
import dev.lcy0x1.decorator.instance.get_numeric_attribute.GetNumericAttributeContext;

public class Test {

	public static void main(String[] args) {
		DecoratorRegistry.register();
		Duel duel = new Duel();
		Card card = new Card(2000, 1000);
		duel.addDecorator(new SimplePermanentAddValueDecorator(AttributeType.DEF, 500));
		duel.addDecorator(new SimplePermanentSwitchStatDecorator());
		duel.addDecorator(new SimplePermanentMultiplyValueDecorator(AttributeType.ATK, 2));
		int value = duel.getHandler(DecoratorRegistry.GET_NUMERIC_ATTRIBUTE)
				.apply(new GetNumericAttributeContext(AttributeType.ATK, card)).value();
		System.out.println(value);
	}

}
