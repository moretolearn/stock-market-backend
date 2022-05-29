package com.cts.fse.stockmarket.commond.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.fse.stockmarket.commond.bean.CompanyCreation;
import com.cts.fse.stockmarket.commond.bean.StockCreation;
import com.cts.fse.stockmarket.commond.service.CompanyCommandService;
import com.cts.fse.stockmarket.commonutils.ApiResponse;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyCommandController<T> {

	@Autowired
	CompanyCommandService companyService;

	@PostMapping("/add")
	public ResponseEntity<?> addCompany(@RequestBody @Valid CompanyCreation company) {

		CompanyCreation addCompany = companyService.addCompany(company);
		if (addCompany.getCompanyCode() != 0) {
			return new ResponseEntity<>(
					new ApiResponse<>(HttpStatus.CREATED.value(), true, "Company Added Successfully", addCompany),
					HttpStatus.CREATED);
		}
		return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), true, "Company Not Added", null),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteCompany(@PathVariable(value = "id") int companyId) throws Exception {
		return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NO_CONTENT.value(), true,
				"Company Deleted Successffully ", companyService.deleteCompany(companyId)), HttpStatus.NO_CONTENT);
	}

}