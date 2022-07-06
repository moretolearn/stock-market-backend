package com.cts.fse.stockmarket.command.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.cts.fse.stockmarket.command.bean.CompanyCreation;
import com.cts.fse.stockmarket.command.service.CompanyCommandService;
import com.cts.fse.stockmarket.commonutils.ApiResponse;

import javax.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyCommandController<T> {

	@Autowired
	CompanyCommandService companyService;

	@PostMapping("/add")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
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

	@PutMapping("/{companyCode}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> updateCompany(@Valid @RequestBody CompanyCreation company,
			@PathVariable Long companyCode) {
		CompanyCreation companyQuery = companyService.updateCompany(company, companyCode);
		if (null == companyQuery)
			return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), true, "Company Not Updated", null),
					HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<>(
					new ApiResponse<>(HttpStatus.OK.value(), true, "Company Updated Successffully ", companyQuery),
					HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<?> deleteCompany(@PathVariable(value = "id") Long companyId) throws Exception {
		return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), true, "Company Deleted Successffully ",
				companyService.deleteCompany(companyId)), HttpStatus.OK);
	}

}