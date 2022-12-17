package dev.lcy0x1.predicate.syntax;

import dev.lcy0x1.predicate.instance.IOperandInstance;
import dev.lcy0x1.predicate.instance.PredicateContext;

import java.util.List;

public interface IOperation {

	default <O, I> IOperandInstance<O> process(PredicateContext ctx, IOperandInstance<I> obj) {
		throw new RuntimeException("invalid operation");
	}

	default <O, I> IOperandInstance<O> process(PredicateContext ctx, List<IOperandInstance<I>> obj) {
		throw new RuntimeException("invalid operation");
	}


	default <O, A, B> IOperandInstance<O> process(PredicateContext ctx, IOperandInstance<A> a, IOperandInstance<B> b) {
		throw new RuntimeException("invalid operation");
	}


	default <O, A, B> IOperandInstance<O> process(PredicateContext ctx, List<IOperandInstance<A>> a, List<IOperandInstance<B>> b) {
		throw new RuntimeException("invalid operation");
	}


	default <O, A, B> IOperandInstance<O> process(PredicateContext ctx, List<IOperandInstance<A>> a, IOperandInstance<B> b) {
		throw new RuntimeException("invalid operation");
	}


	default <O, A, B> IOperandInstance<O> process(PredicateContext ctx, IOperandInstance<A> a, List<IOperandInstance<B>> b) {
		throw new RuntimeException("invalid operation");
	}

}
