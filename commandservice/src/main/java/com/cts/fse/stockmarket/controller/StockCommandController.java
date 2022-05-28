package com.cts.fse.stockmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.fse.stockmarket.bean.CompanyCreation;
import com.cts.fse.stockmarket.bean.StockCreation;
import com.cts.fse.stockmarket.repository.CompanyCommandRepository;
import com.cts.fse.stockmarket.service.StockCommandService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/stock")
public class StockCommandController {

    @Autowired
    StockCommandService stockService;

    @Autowired
    CompanyCommandRepository companyRepository;

    @PostMapping("/add/{companyId}")
    public ResponseEntity<String> addStock(@PathVariable() int companyId, @RequestBody StockCreation stock) {
        Optional<CompanyCreation> findById = companyRepository.findById(companyId);
        stock.setCompanyCreation(findById.get());
        stockService.addStock(stock);
        return new ResponseEntity<>("Stock added successfully", HttpStatus.CREATED);
    }
}
