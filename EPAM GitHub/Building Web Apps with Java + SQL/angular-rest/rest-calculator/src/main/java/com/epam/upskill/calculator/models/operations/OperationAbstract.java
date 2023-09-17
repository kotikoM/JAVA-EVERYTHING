package com.epam.upskill.calculator.models.operations;

import com.epam.upskill.calculator.models.Result;
import com.epam.upskill.calculator.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;

public abstract class OperationAbstract implements OperationInterface {
	
	private static final Logger logger = LogManager.getLogger(OperationAbstract.class);
	
	protected BigDecimal operand1;
	protected BigDecimal operand2;

	public BigDecimal getOperand1() {
		return operand1;
	}

	public BigDecimal getOperand2() {
		return operand2;
	}

	public static Logger getLogger() {
		return logger;
	}

	public String toString(String message) {
		return message + " operation [getOperand1()=" + getOperand1() + ", getOperand2()=" + getOperand2() + "]";
	}

	Result operandsValidator() {
		Result result = new Result();
		if (this.operand1 == null || this.operand2 == null){
			result.setError(Boolean.TRUE);
			result.setMessage(Constants.ERROR_MESSAGE_OPERAND_NOT_VALID);
			getLogger().error("One of the operands is not valid" + this.toString());
		}
		return result;
	}

}
