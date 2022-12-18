package dev.lcy0x1.predicate.util;

import java.util.List;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class ListHelper {

	public static <T, U> U reduce(U u, List<T> list, BiFunction<U, T, U> func) {
		for (T t : list) {
			u = func.apply(u, t);
		}
		return u;
	}

	public static <T, U> U collect(U u, List<T> list, BiConsumer<U, T> func) {
		for (T t : list) {
			func.accept(u, t);
		}
		return u;
	}

	public static boolean eq(List<Integer> list) {
		return new TreeSet<>(list).size() == 1;
	}

	public static boolean neq(List<Integer> list) {
		return new TreeSet<>(list).size() == list.size();
	}

}
