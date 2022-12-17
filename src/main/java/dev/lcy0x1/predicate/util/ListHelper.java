package dev.lcy0x1.predicate.util;

import java.util.List;
import java.util.function.BiFunction;

public class ListHelper {

	public static <T, U> U reduce(U u, List<T> list, BiFunction<U, T, U> func) {
		for (T t : list) {
			u = func.apply(u, t);
		}
		return u;
	}

}
