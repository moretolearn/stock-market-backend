package com.cts.fse.stockmarket.commonutils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResopnse {

	private String errMsg;
	
	private String errCode;
	
	private String urlPath;
	
}
