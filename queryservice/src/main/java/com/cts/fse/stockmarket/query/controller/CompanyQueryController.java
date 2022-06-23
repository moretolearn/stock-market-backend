package com.cts.fse.stockmarket.query.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.fse.stockmarket.commonutils.ApiResponse;
import com.cts.fse.stockmarket.query.service.CompanyQueryService;

@RestController
@RequestMapping("/company")
public class CompanyQueryController {

	@Autowired
	private CompanyQueryService companyQueryService;

	@GetMapping(path = "/getall")
	public ResponseEntity<?> getAllcompanyDetails() {
		return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), true, "Company List Retrived Successfully",
				companyQueryService.getAllCompanies()), HttpStatus.OK);
	}

	@GetMapping(path = "/info/{companyCode}")
	public ResponseEntity<?> getSingleCompanyDetails(@PathVariable("companyCode") Long companyCode) throws Exception {
		return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), true,
				"Single Company Retrived Successfully", companyQueryService.getSingleCompanybyCompanyId(companyCode)),
				HttpStatus.OK);
	}
}
