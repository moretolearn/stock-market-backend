package com.cts.fse.stockmarket.query.service;

import com.cts.fse.stockmarket.query.bean.CompanyQuery;
import com.cts.fse.stockmarket.query.bean.StockQuery;

import lombok.Data;

@Data
public class ResponsData {
	
	private long companyCode;
	
	private long stockCode;
	
	private CompanyQuery company;
	
	private StockQuery stock;

}
