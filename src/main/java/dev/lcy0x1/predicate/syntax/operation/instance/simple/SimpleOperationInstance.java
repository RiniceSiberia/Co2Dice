package dev.lcy0x1.predicate.syntax.operation.instance.simple;

import dev.lcy0x1.predicate.syntax.operation.instance.api.IOperationInstance;
import dev.lcy0x1.predicate.syntax.param.instance.IParamInstance;
import dev.lcy0x1.predicate.syntax.param.type.IParam;
import dev.lcy0x1.predicate.syntax.param.type.ParameterSet;
import dev.lcy0x1.predicate.syntax.type.OperandType;

import java.util.List;

public abstract class SimpleOperationInstance<O> implements IOperationInstance {

	private final OperandType<O> output;
	private final List<IParamInstance<?>> params;

	public SimpleOperationInstance(OperandType<O> output, List<IParamInstance<?>> params) {
		this.output = output;
		this.params = params;
	}

	public OperandType<O> getOutputType() {
		return output;
	}

	public abstract void verify(ParameterSet set);

	public List<IParam> getParams() {
		return params.stream().map(IParamInstance::param).toList();
	}

}
