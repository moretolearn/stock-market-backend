package com.cts.fse.stockmarket.query.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.fse.stockmarket.commonutils.ApiResponse;
import com.cts.fse.stockmarket.query.bean.CompanyQuery;
import com.cts.fse.stockmarket.query.service.CompanyQueryService;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyQueryController {

	@Autowired
	private CompanyQueryService companyQueryService;

	@GetMapping(path = "/getall")
	public ResponseEntity<?> getAllcompanyDetails() {
		return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), true, "Company List Retrived Successfully",
				companyQueryService.getAllCompanies()), HttpStatus.OK);
	}

	@GetMapping(path = "/info/{companyCode}")
	public ResponseEntity<?> getSingleCompanyDetails(@PathVariable("companyCode") int companyCode) throws Exception {
		return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(), true,
				"Single Company Retrived Successfully", companyQueryService.getSingleCompanybyCompanyId(companyCode)),
				HttpStatus.OK);
	}
	
    @PostMapping("/add")
    public ResponseEntity<?> createCompany(@Valid @RequestBody CompanyQuery company) {

        CompanyQuery savedCompany = companyQueryService.createCompany(company);

        return new ResponseEntity<>(savedCompany,HttpStatus.CREATED);
    }

    @PutMapping("/{companyCode}")
    public ResponseEntity<CompanyQuery> updateCompany(@Valid @RequestBody CompanyQuery company, @PathVariable int  companyCode) {
        CompanyQuery companyQuery = companyQueryService.updateCompany(company, companyCode);
        if(null==companyQuery)
            return ResponseEntity.unprocessableEntity().build();
        else
            return new ResponseEntity<>(companyQuery,HttpStatus.CREATED);
    }

    @DeleteMapping("/{companyCode}")
    public ResponseEntity<String> deleteCompany(@PathVariable int  companyCode) {
        boolean isDeleted = companyQueryService.deleteCompany(companyCode);
        if(!isDeleted)
            return new ResponseEntity<>("company not found with Id", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("company deleted successfully",HttpStatus.OK);
    }

}
