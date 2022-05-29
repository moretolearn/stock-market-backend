package com.cts.fse.stockmarket.query.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.cts.fse.stockmarket.commonutils.ApiResponse;
import com.cts.fse.stockmarket.query.bean.StockQuery;
import com.cts.fse.stockmarket.query.service.StockQueryService;

@RestController
@RequestMapping("/api/v1/stock")
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
	public ResponseEntity<?> getSingleStockDetails(@PathVariable("stockCode") int stockCode) throws Exception {
		return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), true, "Stock List Retrived Successfully",
				stockQueryService.getSingleStockbyId(stockCode)), HttpStatus.OK);
	}
	
    @GetMapping("/get/{companyCode}/{startDate}/{endDate}")
    public ResponseEntity<List<StockQuery>> findAllStocksBetweenDates(@PathVariable(value = "companyCode") int companyCode, @PathVariable(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                                      @PathVariable(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
        return new ResponseEntity<>(stockQueryService.findAllStocksBetweenDates(companyCode, startDate, endDate),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<StockQuery> addStock(@RequestBody StockQuery stockQuery){
        StockQuery addStock = stockQueryService.addstock(stockQuery);
        if(null==addStock)
            return ResponseEntity.unprocessableEntity().build();

        return new ResponseEntity<>(stockQuery, HttpStatus.CREATED);
    }

    @PutMapping("/{stockCode}")
    public ResponseEntity<StockQuery> updateStock(@Valid @RequestBody StockQuery stockQuery, @PathVariable int  stockCode) {
        StockQuery updateStock = stockQueryService.updateStock(stockQuery, stockCode);
        if(null==updateStock)
            return ResponseEntity.unprocessableEntity().build();
        else
            return new ResponseEntity<>(updateStock,HttpStatus.CREATED);
    }

    @DeleteMapping("/{stockCode}")
    public ResponseEntity<String> deleteStock(@PathVariable int  stockCode) {
        boolean isDeleted = stockQueryService.deleteStock(stockCode);
        if(!isDeleted)
            return new ResponseEntity<>("stock not found with Id", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("stock deleted successfully",HttpStatus.OK);
    }



}