package com.akshay.bankSystem.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.akshay.bankSystem.payloads.response.ApiResponse;
import com.akshay.bankSystem.services.TransactionServices;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@Autowired
	private TransactionServices services;
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundHandler(ResourceNotFoundException ex){
		
		String message=ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(message, false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex)
	{
		Map<String, String>errors=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		
		return new ResponseEntity<Map<String,String>>(errors,HttpStatus.BAD_REQUEST); 
	}
	
	@ExceptionHandler(ApiException.class)
	public ResponseEntity<ApiResponse> handlerApiException(ApiException ex){
		String messageString = ex.getMessage();
		ApiResponse apiResponse = new ApiResponse(messageString, false);
		
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<TransactionError> transactionException(TransactionException tx){
		
		String message = tx.getMessage();
		return new ResponseEntity<TransactionError>(new TransactionError(message),HttpStatus.BAD_REQUEST);
	}
	
}
