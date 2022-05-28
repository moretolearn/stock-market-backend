package com.cts.fse.stockmarket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.fse.stockmarket.ApiResponse;
import com.cts.fse.stockmarket.bean.CompanyCreation;
import com.cts.fse.stockmarket.bean.StockCreation;
import com.cts.fse.stockmarket.service.CompanyCommandService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyCommandController<T> {

    @Autowired
    CompanyCommandService companyService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<T>> addCompany(@RequestBody CompanyCreation company) {

        CompanyCreation addCompany = companyService.addCompany(company);
        if(addCompany.getCompanyCode()!=0) {
        	return new ResponseEntity<>(new ApiResponse<>(HttpStatus.CREATED.value(), true, "Company Added Successfully", addCompany), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new ApiResponse<>(HttpStatus.NO_CONTENT.value(), true, "Company Added Successfully", addCompany), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable(value = "id") int companyId)
            throws Exception {
        return new ResponseEntity<>(companyService.deleteCompany(companyId),HttpStatus.OK);
    }

    @GetMapping("/get/{companyCode}/{startDate}/{endDate}")
    public ResponseEntity<List<StockCreation>> findAllStocksBetweenDates(@PathVariable(value = "companyCode") int companyCode, @PathVariable(value = "startDate") Date startDate,
                                                                         @PathVariable(value = "endDate") Date endDate){
        return new ResponseEntity<List<StockCreation>>(companyService.findAllStocksBetweenDates(companyCode, startDate, endDate),HttpStatus.OK);
    }
}