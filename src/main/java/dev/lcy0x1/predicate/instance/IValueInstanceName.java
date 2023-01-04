package dev.lcy0x1.predicate.instance;

import dev.lcy0x1.predicate.syntax.type.NamedEnum;
import dev.lcy0x1.util.CastHelper;

public interface IValueInstanceName<T extends NamedEnum> extends IValueInstance<T> {

	static <I extends NamedEnum> IValueInstanceName<I> asEnum(IValueInstance<I> obj) {
		return CastHelper.unsafeCast(obj);
	}

	T preview();

}
