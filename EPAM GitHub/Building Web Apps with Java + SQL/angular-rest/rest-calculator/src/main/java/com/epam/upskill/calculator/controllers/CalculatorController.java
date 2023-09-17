package com.epam.upskill.calculator.controllers;

import com.epam.upskill.calculator.models.Result;
import com.epam.upskill.calculator.models.operations.MultiplyOperation;
import com.epam.upskill.calculator.models.operations.OperationInterface;
import com.epam.upskill.calculator.models.operations.SubtractOperation;
import com.epam.upskill.calculator.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epam.upskill.calculator.models.operations.AddOperation;
import com.epam.upskill.calculator.models.operations.DivideOperation;


@RestController
@RequestMapping(Constants.CALCULATOR_PATH)
@CrossOrigin()
public class CalculatorController {
	
	
	private static final Logger logger = LogManager.getLogger(CalculatorController.class);

	@PostMapping("/add")
	public Result add(@RequestBody AddOperation operation) {
		return this.doOperation(operation);
	}
	

	@PostMapping("/subtract")
	public Result subtract(@RequestBody SubtractOperation operation) {
		return this.doOperation(operation);
	}

	@PostMapping("/multiply")
	public Result multiply(@RequestBody MultiplyOperation operation) {
		return this.doOperation(operation);
	}

	@PostMapping("/divide")
	public Result divide(@RequestBody DivideOperation operation) {
		return this.doOperation(operation);
	}

	private Result doOperation(OperationInterface operation){
		Result result = null;
		try {
			logger.info("Executing the operation.");
			result = operation.perform(); }
		catch (Exception ex) {
			logger.error("Error during execution of doOperation method: ", ex);
			result.setError(Boolean.TRUE);
			result.setMessage("Ops! something wrong happened while executing doOperation!");
		}
		logger.info("Final operation result : " + result.toString());
		return result;
	}

}
