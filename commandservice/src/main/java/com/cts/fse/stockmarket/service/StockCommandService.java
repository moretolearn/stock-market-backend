package com.cts.fse.stockmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.stockmarket.bean.StockCreation;
import com.cts.fse.stockmarket.repository.StockCommandRepository;

@Service
public class StockCommandService {

    @Autowired
    StockCommandRepository stockCommandRepository;

    public void addStock(StockCreation stock) {
        stockCommandRepository.save(stock);
    }
}
