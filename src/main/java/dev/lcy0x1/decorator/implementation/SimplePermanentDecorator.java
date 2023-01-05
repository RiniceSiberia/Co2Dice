package dev.lcy0x1.decorator.implementation;

import dev.lcy0x1.decorator.api.Decorator;
import dev.lcy0x1.decorator.api.DecoratorContext;
import dev.lcy0x1.decorator.api.DecoratorModifier;
import dev.lcy0x1.decorator.api.DecoratorValueInstance;

public abstract class SimplePermanentDecorator<
		D extends Decorator<D, C, V>,
		C extends Record & DecoratorContext<C>,
		V extends Record & DecoratorValueInstance<V>
		> extends DecoratorModifier<D, C, V> {

	public boolean isValid() {
		return true;
	}

}
