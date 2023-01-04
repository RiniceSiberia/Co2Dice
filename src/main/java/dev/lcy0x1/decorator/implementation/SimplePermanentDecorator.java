package dev.lcy0x1.decorator.implementation;

import dev.lcy0x1.decorator.api.DecoratorModifier;

public class SimplePermanentDecorator extends DecoratorModifier {

	public boolean isValid() {
		return true;
	}

}
