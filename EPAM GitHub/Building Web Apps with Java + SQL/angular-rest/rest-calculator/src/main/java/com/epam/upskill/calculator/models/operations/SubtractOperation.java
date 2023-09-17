package com.epam.upskill.calculator.models.operations;

import com.epam.upskill.calculator.models.Result;

import java.math.BigDecimal;

public class SubtractOperation extends OperationAbstract {

	@Override
	public Result perform() throws Exception {
		Result result = new Result();
		result.setResult(BigDecimal.ZERO);
		return result;
	}
	
	@Override
	public String toString() {
		return super.toString("Subtract ");
	}

}
