package com.cts.fse.stockmarket.commonutils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModelValidations {

	private String defaultMessage;
	
	private String objectName;
	
	private String field;
	
	private Object rejectedValue;
	
	private String code;
	
}

