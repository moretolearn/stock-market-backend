package com.cts.fse.stockmarket.command.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cts.fse.stockmarket.command.bean.StockCreation;
import com.cts.fse.stockmarket.command.repository.CompanyCommandRepository;
import com.cts.fse.stockmarket.command.service.StockCommandService;
import com.cts.fse.stockmarket.commonutils.ApiResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/stock")
public class StockCommandController {

	@Autowired
	StockCommandService stockService;

	@Autowired
	CompanyCommandRepository companyRepository;

	@PostMapping("/add/{companyId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> addStock(@PathVariable() Long companyId, @RequestBody @Valid StockCreation stock) {
		System.err.println("enter ");
//		Optional<CompanyCreation> findById = companyRepository.findById(companyId);
//		System.err.println(findById.get());
//		stock.setCompany(findById.get());
		StockCreation addStock = stockService.addStock(companyId,stock);
		return new ResponseEntity<>(
				new ApiResponse<>(HttpStatus.CREATED.value(), true, "Stock Added Successfully", addStock),
				HttpStatus.CREATED);
	}
	
//	 @PostMapping("/add")
//	    public ResponseEntity<StockCreation> addStock(@RequestBody StockCreation stockQuery){
//		 StockCreation addStock = stockService.addStock(stockQuery);
//	        if(null==addStock)
//	            return ResponseEntity.unprocessableEntity().build();
//
//	        return new ResponseEntity<>(stockQuery, HttpStatus.CREATED);
//	    }

	@DeleteMapping("/{stockCode}/{companyCode}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> deleteStock(@PathVariable Long stockCode,@PathVariable Long companyCode) throws Exception {
		String deleteStock = stockService.deleteStock(stockCode,companyCode);
		return new ResponseEntity<>(
				new ApiResponse<>(HttpStatus.OK.value(), true, "Stock delete Successfully", deleteStock),
				HttpStatus.CREATED);
	}

	@PutMapping("/{companyCode}/{stockCode}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> updateStock(@Valid @RequestBody StockCreation stockCreation, @PathVariable Long companyCode,
			@PathVariable Long stockCode) {
		StockCreation updateStock = stockService.updateStock(stockCreation, companyCode, stockCode);
		if (null == updateStock)
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), true, "Stock not updated Successfully", updateStock),
					HttpStatus.CREATED);
		else
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), true, "Stock updated Successfully", updateStock),
					HttpStatus.CREATED);
	}
}
