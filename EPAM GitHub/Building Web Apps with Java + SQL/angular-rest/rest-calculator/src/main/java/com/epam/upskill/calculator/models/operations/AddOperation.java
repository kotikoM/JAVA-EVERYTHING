package com.epam.upskill.calculator.models.operations;

import com.epam.upskill.calculator.models.Result;

public class AddOperation extends OperationAbstract {

	@Override
	public Result perform() throws Exception {
		Result result = operandsValidator();
		if (!result.getError()) {
			result.setResult(operand1.add(operand2));
			result.setError(Boolean.FALSE);
		}
		return result;
	}

	@Override
	public String toString() {
		return super.toString("Add ");
	}
}
