package com.cts.fse.stockmarket.query.dto;

import lombok.Data;

@Data
public class StocksMinMaxAvgDto<T> {

	private Object object;
	
	private StockMinMaxAvgDto stockMinMaxAvgDto;
}
