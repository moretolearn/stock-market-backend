package com.cts.fse.stockmarket.commond.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.fse.stockmarket.commond.bean.CompanyCreation;
import com.cts.fse.stockmarket.commond.bean.StockCreation;
import com.cts.fse.stockmarket.commond.repository.CompanyCommandRepository;
import com.cts.fse.stockmarket.commond.service.StockCommandService;
import com.cts.fse.stockmarket.commonutils.ApiResponse;

import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/stock")
public class StockCommandController {

	@Autowired
	StockCommandService stockService;

	@Autowired
	CompanyCommandRepository companyRepository;

	@PostMapping("/add/{companyId}")
	public ResponseEntity<?> addStock(@PathVariable() int companyId, @RequestBody @Valid StockCreation stock) {
		Optional<CompanyCreation> findById = companyRepository.findById(companyId);
		stock.setCompanyCreation(findById.get());
		StockCreation addStock = stockService.addStock(stock);
		return new ResponseEntity<>(
				new ApiResponse<>(HttpStatus.CREATED.value(), true, "Stock Added Successfully", addStock),
				HttpStatus.CREATED);
	}

	@DeleteMapping("/{stockCode}")
	public ResponseEntity<?> deleteStock(@PathVariable int stockCode) {
		boolean isDeleted = stockService.deleteStock(stockCode);
		if (!isDeleted)
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), true,
					"Stock Not Found With Id " + stockCode, stockCode), HttpStatus.CREATED);
		return new ResponseEntity<>(
				new ApiResponse<>(HttpStatus.CREATED.value(), true, "Stock delete Successfully", isDeleted),
				HttpStatus.CREATED);
	}

	@PutMapping("/{companyCode}/{stockCode}")
	public ResponseEntity<?> updateStock(@Valid @RequestBody StockCreation stockCreation, @PathVariable int companyCode,
			@PathVariable int stockCode) {
		StockCreation updateStock = stockService.updateStock(stockCreation, companyCode, stockCode);
		if (null == updateStock)
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), true, "Stock not updated Successfully", updateStock),
					HttpStatus.CREATED);
		else
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), true, "Stock updated Successfully", updateStock),
					HttpStatus.CREATED);
	}
}
