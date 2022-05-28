package com.cts.fse.stockmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.fse.stockmarket.bean.CompanyCreation;
import com.cts.fse.stockmarket.service.CompanyQueryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyQueryController {
    @Autowired
    private CompanyQueryService companyQueryService;

    @GetMapping(path="/getall")
    public @ResponseBody ResponseEntity<List<CompanyCreation>> getAllcompanyDetails() {

        return new ResponseEntity<>(companyQueryService.getAllCompanies(), HttpStatus.OK);
    }

    @GetMapping(path="/info/{id}")
    public @ResponseBody ResponseEntity<CompanyCreation> getSingleCompanyDetails(@PathVariable int companyCode) throws  Exception {
        return new ResponseEntity<>(companyQueryService.getSingleCompanybyCompanyId(companyCode), HttpStatus.OK);
    }

}
