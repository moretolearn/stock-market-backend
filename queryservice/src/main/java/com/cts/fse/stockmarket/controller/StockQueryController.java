package com.cts.fse.stockmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.fse.stockmarket.commond.bean.StockCreation;
import com.cts.fse.stockmarket.service.StockQueryService;

import java.util.List;
@RestController
@RequestMapping("/api/v1/stock")
public class StockQueryController {

    @Autowired
    private StockQueryService stockQueryService;

    @GetMapping(path = "/getall")
    public @ResponseBody
    ResponseEntity<List<StockCreation>> getAllStocks() {

        return new ResponseEntity<>( stockQueryService.getAllStocks(), HttpStatus.OK);
    }

    @GetMapping(path = "/info/{id}")
    public @ResponseBody
    ResponseEntity<StockCreation> getSingleStockDetails(@PathVariable int stockCode) throws Exception{
        return new ResponseEntity<>(stockQueryService.getSingleStockbyId(stockCode), HttpStatus.OK);
    }

}