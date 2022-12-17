package dev.lcy0x1.predicate.syntax;

import dev.lcy0x1.predicate.instance.IOperandInstance;
import dev.lcy0x1.predicate.instance.PredicateContext;
import dev.lcy0x1.predicate.util.CastHelper;
import dev.lcy0x1.predicate.util.ListHelper;

import java.util.List;

public enum Operator {
	NOT(OperandType.BOOL,
			IOperandParam.getSingleton(OperandType.BOOL, "val"),
			(ctx, e) -> OperandType.BOOL.parse(ctx, c -> !e.getVal(c))
	),
	AND(OperandType.BOOL,
			IOperandParam.getList(OperandType.BOOL, "val", 2),
			(ctx, e) -> OperandType.BOOL.parse(ctx,
					c -> ListHelper.reduce(true, e, (u, x) -> u && x.getVal(c)))
	),
	OR(OperandType.BOOL,
			IOperandParam.getList(OperandType.BOOL, "val", 2),
			(ctx, e) -> OperandType.BOOL.parse(ctx,
					c -> ListHelper.reduce(false, e, (u, x) -> u || x.getVal(c)))
	),
	XOR(OperandType.BOOL,
			IOperandParam.getList(OperandType.BOOL, "val", 2),
			(ctx, e) -> OperandType.BOOL.parse(ctx,
					c -> ListHelper.reduce(false, e, (u, x) -> u ^ x.getVal(c)))
	),
	EQ(OperandType.BOOL,
			IOperandParam.getSingleton(OperandType.NUMBER, "left"),
			IOperandParam.getSingleton(OperandType.NUMBER, "right"),
			(ctx, a, b) -> OperandType.BOOL.parse(ctx, c -> a.getVal(c).equals(b.getVal(c)))
	),
	NEQ(OperandType.BOOL,
			IOperandParam.getSingleton(OperandType.NUMBER, "left"),
			IOperandParam.getSingleton(OperandType.NUMBER, "right"),
			(ctx, a, b) -> OperandType.BOOL.parse(ctx, c -> !a.getVal(c).equals(b.getVal(c)))
	),
	LE(OperandType.BOOL,
			IOperandParam.getSingleton(OperandType.NUMBER, "left"),
			IOperandParam.getSingleton(OperandType.NUMBER, "right"),
			(ctx, a, b) -> OperandType.BOOL.parse(ctx, c -> a.getVal(c) < b.getVal(c))
	),
	LEQ(OperandType.BOOL,
			IOperandParam.getSingleton(OperandType.NUMBER, "left"),
			IOperandParam.getSingleton(OperandType.NUMBER, "right"),
			(ctx, a, b) -> OperandType.BOOL.parse(ctx, c -> a.getVal(c) <= b.getVal(c))
	),
	GE(OperandType.BOOL,
			IOperandParam.getSingleton(OperandType.NUMBER, "left"),
			IOperandParam.getSingleton(OperandType.NUMBER, "right"),
			(ctx, a, b) -> OperandType.BOOL.parse(ctx, c -> a.getVal(c) > b.getVal(c))
	),
	GEQ(OperandType.BOOL,
			IOperandParam.getSingleton(OperandType.NUMBER, "left"),
			IOperandParam.getSingleton(OperandType.NUMBER, "right"),
			(ctx, a, b) -> OperandType.BOOL.parse(ctx, c -> a.getVal(c) >= b.getVal(c))
	),
	ADD(OperandType.NUMBER,
			IOperandParam.getList(OperandType.NUMBER, "val", 2),
			(ctx, e) -> OperandType.NUMBER.parse(ctx,
					c -> ListHelper.reduce(0, e, (u, x) -> u + x.getVal(c)))
	),
	SUBTRACT(OperandType.NUMBER,
			IOperandParam.getSingleton(OperandType.NUMBER, "left"),
			IOperandParam.getSingleton(OperandType.NUMBER, "right"),
			(ctx, a, b) -> OperandType.NUMBER.parse(ctx, c -> a.getVal(c) - b.getVal(c))
	),
	MULTIPLY(OperandType.NUMBER,
			IOperandParam.getList(OperandType.NUMBER, "val", 2),
			(ctx, e) -> OperandType.NUMBER.parse(ctx,
					c -> ListHelper.reduce(0, e, (u, x) -> u * x.getVal(c)))
	);

	private final OperandType<?> output;
	private final List<IOperandParam<?>> params;
	private final IOperation operation;

	<O, I> Operator(OperandType<O> output, _OperandParamSingleton<I> param, SingleSingleton<O, I> func) {
		this.output = output;
		this.params = List.of(param);
		this.operation = func;
	}

	<O, I> Operator(OperandType<O> output, _OperandParamList<I> param, SingleList<O, I> func) {
		this.output = output;
		this.params = List.of(param);
		this.operation = func;
	}

	<O, A, B> Operator(OperandType<O> output,
					   _OperandParamSingleton<A> first,
					   _OperandParamSingleton<B> second,
					   DoubleSingleton<O, A, B> func
	) {
		this.output = output;
		this.params = List.of(first, second);
		this.operation = func;
	}

	<O, A, B> Operator(OperandType<O> output,
					   _OperandParamList<A> first,
					   _OperandParamList<B> second,
					   DoubleList<O, A, B> func
	) {
		this.output = output;
		this.params = List.of(first, second);
		this.operation = func;
	}

	<O, A, B> Operator(OperandType<O> output,
					   _OperandParamList<A> first,
					   _OperandParamSingleton<B> second,
					   DoubleLS<O, A, B> func
	) {
		this.output = output;
		this.params = List.of(first, second);
		this.operation = func;
	}

	<O, A, B> Operator(OperandType<O> output,
					   _OperandParamSingleton<A> first,
					   _OperandParamList<B> second,
					   DoubleSL<O, A, B> func
	) {
		this.output = output;
		this.params = List.of(first, second);
		this.operation = func;
	}

	public <I> boolean verify(IOperandInstance<I> obj) {
		return params.size() == 1 && params.get(0).type().equals(obj.getType());
	}

	public <A, B> boolean verify(IOperandInstance<A> a, IOperandInstance<B> b) {
		return params.size() == 2 &&
				params.get(0).type().equals(a.getType()) &&
				params.get(1).type().equals(b.getType());
	}

	interface SingleSingleton<O, I> extends IOperation {

		default <X, Y> IOperandInstance<X> process(PredicateContext ctx, IOperandInstance<Y> obj) {
			return CastHelper.cast(apply(ctx, CastHelper.cast(obj)));
		}

		IOperandInstance<O> apply(PredicateContext ctx, IOperandInstance<I> obj);

	}

	interface SingleList<O, I> extends IOperation {

		default <X, Y> IOperandInstance<X> process(PredicateContext ctx, List<IOperandInstance<Y>> obj) {
			return CastHelper.cast(apply(ctx, CastHelper.cast(obj)));
		}

		IOperandInstance<O> apply(PredicateContext ctx, List<IOperandInstance<I>> obj);

	}

	interface DoubleSingleton<O, A, B> extends IOperation {

		default <X, Y, Z> IOperandInstance<X> process(PredicateContext ctx, IOperandInstance<Y> a, IOperandInstance<Z> b) {
			return CastHelper.cast(apply(ctx, CastHelper.cast(a), CastHelper.cast(b)));
		}

		IOperandInstance<O> apply(PredicateContext ctx, IOperandInstance<A> a, IOperandInstance<B> b);

	}

	interface DoubleList<O, A, B> extends IOperation {

		default <X, Y, Z> IOperandInstance<X> process(PredicateContext ctx, List<IOperandInstance<Y>> a, List<IOperandInstance<Z>> b) {
			return CastHelper.cast(apply(ctx, CastHelper.cast(a), CastHelper.cast(b)));
		}

		IOperandInstance<O> apply(PredicateContext ctx, List<IOperandInstance<A>> a, List<IOperandInstance<B>> b);

	}

	interface DoubleLS<O, A, B> extends IOperation {

		default <X, Y, Z> IOperandInstance<X> process(PredicateContext ctx, List<IOperandInstance<Y>> a, IOperandInstance<Z> b) {
			return CastHelper.cast(apply(ctx, CastHelper.cast(a), CastHelper.cast(b)));
		}

		IOperandInstance<O> apply(PredicateContext ctx, List<IOperandInstance<A>> a, IOperandInstance<B> b);

	}

	interface DoubleSL<O, A, B> extends IOperation {

		default <X, Y, Z> IOperandInstance<X> process(PredicateContext ctx, IOperandInstance<Y> a, List<IOperandInstance<Z>> b) {
			return CastHelper.cast(apply(ctx, CastHelper.cast(a), CastHelper.cast(b)));
		}

		IOperandInstance<O> apply(PredicateContext ctx, IOperandInstance<B> a, List<IOperandInstance<A>> b);

	}

}
