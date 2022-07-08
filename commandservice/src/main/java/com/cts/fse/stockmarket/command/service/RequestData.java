package com.cts.fse.stockmarket.command.service;

import com.cts.fse.stockmarket.command.bean.CompanyCreation;
import com.cts.fse.stockmarket.command.bean.StockCreation;

import lombok.Data;

@Data
public class RequestData {
	
	private long companyCode;
	
	private long stockCode;
	
	private CompanyCreation company;
	
	private StockCreation stock;

}
