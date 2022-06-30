package com.cts.fse.stockmarket.query.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cts.fse.stockmarket.commonutils.ApiResponse;
import com.cts.fse.stockmarket.query.service.StockQueryService;

@RestController
@RequestMapping("/stock")
@Validated
public class StockQueryController {

	@Autowired
	private StockQueryService stockQueryService;

	@GetMapping(path = "/getall")
	public ResponseEntity<?> getAllStocks() {

		return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), true, "Stock List Retrived Successfully",
				stockQueryService.getAllStocks()), HttpStatus.OK);
	}

	@GetMapping(value = "/info/{stockCode}")
	public ResponseEntity<?> getSingleStockDetails(@PathVariable("stockCode") Long stockCode) throws Exception {
		return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), true, "Stock List Retrived Successfully",
				stockQueryService.getSingleStockbyId(stockCode)), HttpStatus.OK);
	}
	
    @GetMapping("/get/{companyCode}/{startDate}/{endDate}")
    public ResponseEntity<?> findAllStocksBetweenDates(@PathVariable(value = "companyCode") Long companyCode, @PathVariable(value = "startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date startDate,
                                                                      @PathVariable(value = "endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Date endDate){
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), true, "Stock List Retrived Successfully",
				stockQueryService.findAllStocksBetweenDates(companyCode, startDate, endDate)), HttpStatus.OK);
    }

    @GetMapping("/getall/{companyCode}")
    public ResponseEntity<?> findAllStocksBYCompanyCode(@PathVariable(value = "companyCode") Long companyCode){
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), true, "Stock List Retrived Successfully",
				stockQueryService.findAllStocksByCompanyCode(companyCode)), HttpStatus.OK);
    }

}