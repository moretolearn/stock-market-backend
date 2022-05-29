package com.cts.fse.stockmarket.commonutils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

public class GlobalExceptionHandlerMapper {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> getGlobalException(WebRequest wr, Exception ex) {
		String uri = ((ServletWebRequest) wr).getRequest().getRequestURI().toString();
		ErrorResopnse errorResopnse = new ErrorResopnse(ex.getMessage(), null, uri);
		return new ResponseEntity<>(
				new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), false, "Global Exception Occured", errorResopnse),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> getMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		List<ModelValidations> arrayList = new ArrayList<>();
		fieldErrors.stream().forEach(fieldError -> {
			ModelValidations modelValidations = new ModelValidations(fieldError.getDefaultMessage(),
					fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(),
					fieldError.getCode());
			arrayList.add(modelValidations);
		});
		return new ResponseEntity<>(new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), false,
				"Method Argument Not Valid Exception Occured", arrayList), HttpStatus.BAD_REQUEST);
	}
}
