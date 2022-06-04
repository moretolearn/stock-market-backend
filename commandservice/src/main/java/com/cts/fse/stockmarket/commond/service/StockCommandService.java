package com.cts.fse.stockmarket.commond.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.stockmarket.commond.bean.CompanyCreation;
import com.cts.fse.stockmarket.commond.bean.StockCreation;
import com.cts.fse.stockmarket.commond.repository.CompanyCommandRepository;
import com.cts.fse.stockmarket.commond.repository.StockCommandRepository;

@Service
public class StockCommandService {

    @Autowired
    StockCommandRepository stockCommandRepository;
    
    @Autowired
    CompanyCommandRepository companyCommandRepository;

    public StockCreation addStock(StockCreation stock) {
        stockCommandRepository.save(stock);
        return stock;
    }
    
    public boolean deleteStock(int stockCode) {
    	 Optional<StockCreation> findById = stockCommandRepository.findById(stockCode);
         if(!findById.isPresent())
            return false;
         stockCommandRepository.deleteById(stockCode);
             return true;
    }
    
    public StockCreation updateStock(StockCreation stockCreation,int companyCode, int stockCode){
        Optional<CompanyCreation> findById = companyCommandRepository.findById(companyCode);
        if(!findById.isPresent())
            return null;
        Optional<StockCreation> stockCreationOpt= stockCommandRepository.findById(stockCode);
        if(!stockCreationOpt.isPresent())
            return null;

        stockCreation.setCompanyCreation(findById.get());
        stockCreation.setStockCode(stockCreationOpt.get().getStockCode());
        StockCreation save = stockCommandRepository.save(stockCreation);
        return save;
    }
}
