package com.cts.fse.stockmarket.commonutils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

	private int statusCode;
    private Boolean status;
    private String message;
    private Object result; 
}
