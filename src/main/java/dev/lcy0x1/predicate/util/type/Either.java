package dev.lcy0x1.predicate.util.type;

import java.util.Optional;
import java.util.function.Function;

public interface Either<A, B> {

	static <A, B> Either<A, B> ofLeft(A a) {
		return new Left<>(a);
	}

	static <A, B> Either<A, B> ofRight(B b) {
		return new Right<>(b);
	}

	Optional<A> getLeft();

	Optional<B> getRight();

	<T> T map(Function<A, T> fa, Function<B, T> fb);

	record Left<A, B>(A a) implements Either<A, B> {

		@Override
		public Optional<A> getLeft() {
			return Optional.of(a);
		}

		@Override
		public Optional<B> getRight() {
			return Optional.empty();
		}

		@Override
		public <T> T map(Function<A, T> fa, Function<B, T> fb) {
			return fa.apply(a);
		}

	}

	record Right<A, B>(B b) implements Either<A, B> {

		@Override
		public Optional<A> getLeft() {
			return Optional.empty();
		}

		@Override
		public Optional<B> getRight() {
			return Optional.of(b);
		}

		@Override
		public <T> T map(Function<A, T> fa, Function<B, T> fb) {
			return fb.apply(b);
		}

	}

}
