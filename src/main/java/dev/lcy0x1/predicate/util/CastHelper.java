package dev.lcy0x1.predicate.util;

import dev.lcy0x1.predicate.instance.IValueInstance;
import dev.lcy0x1.predicate.instance.IValueInstanceList;

import java.util.List;

public class CastHelper {

	public static <A, B> IValueInstance<B> cast(IValueInstance<A> a) {
		return unsafeCast(a);
	}

	public static <A, B> IValueInstanceList<B> cast(IValueInstanceList<A> a) {
		return unsafeCast(a);
	}

	public static <I> IValueInstanceList<I> castList(IValueInstance<List<I>> a) {
		return unsafeCast(a);
	}

	@SuppressWarnings("unchecked")
	public static <A, B> B unsafeCast(A a) {
		return (B) a;
	}

}
