package com.cts.fse.stockmarket.query.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.stockmarket.query.bean.CompanyQuery;
import com.cts.fse.stockmarket.query.bean.StockQuery;
import com.cts.fse.stockmarket.query.dto.StockMinMaxAvgDto;
import com.cts.fse.stockmarket.query.dto.StocksMinMaxAvgDto;
import com.cts.fse.stockmarket.query.repository.CompanyQueryRepository;
import com.cts.fse.stockmarket.query.repository.StockQueryRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StockQueryService {

    @Autowired
    StockQueryRepository stockQueryRepository;
    
    @Autowired
    CompanyQueryRepository companyQueryRepository;


    public List<StockQuery> getAllStocks()
    {
        List<StockQuery> stocks = new ArrayList<StockQuery>();
        stockQueryRepository.findAll().forEach(stock -> stocks.add(stock));
        return stocks;
    }

    public Optional<StockQuery> getSingleStockbyId(int stockId) throws Exception{
        return stockQueryRepository.findById(stockId);
    }
    
    public StocksMinMaxAvgDto<?> findAllStocksBetweenDates(int companyCode, Date startDate, Date endDate){
        Optional<CompanyQuery> companyQueryOptional = companyQueryRepository.findById(companyCode);
        if(!companyQueryOptional.isPresent())
            return  null;
        List<StockQuery> stocksCreatedOnBetween = stockQueryRepository
                .findByCompanyQueryCompanyCodeAndCreatedOnBetween(companyCode, startDate, endDate);
        StockMinMaxAvgDto findStocksByMinMaxAvgByDates = stockQueryRepository.findStocksByMinMaxAvgByDates(companyCode, startDate, endDate);
        StocksMinMaxAvgDto<?> stocksMinMaxAvgDto = new StocksMinMaxAvgDto();
        stocksMinMaxAvgDto.setObject(stocksCreatedOnBetween);
        stocksMinMaxAvgDto.setStockMinMaxAvgDto(findStocksByMinMaxAvgByDates);
        return stocksMinMaxAvgDto;
    }

    public StockQuery addstock(StockQuery stockQuery){
        Optional<CompanyQuery> companyQueryOptional= companyQueryRepository.findById(stockQuery.getCompanyQuery().getCompanyCode());
        if(!companyQueryOptional.isPresent())
            return null;
        stockQuery.setCompanyQuery(companyQueryOptional.get());
        stockQueryRepository.save(stockQuery);
        return stockQuery;
    }

    public StockQuery updateStock(StockQuery stockQuery, int stockCode){
        Optional<CompanyQuery> companyQueryOptional= companyQueryRepository.findById(stockQuery.getCompanyQuery().getCompanyCode());
        if(!companyQueryOptional.isPresent())
            return null;
        Optional<StockQuery> stockQueryOptional= stockQueryRepository.findById(stockCode);
        if(!stockQueryOptional.isPresent())
            return null;

        stockQuery.setCompanyQuery(companyQueryOptional.get());
        stockQuery.setStockCode(stockQueryOptional.get().getStockCode());
        stockQueryRepository.save(stockQuery);
        return stockQuery;
    }

    public boolean deleteStock(int stockCode){
        Optional<StockQuery> stockQueryOptional=stockQueryRepository.findById(stockCode);
        if(!stockQueryOptional.isPresent())
            return false;
        stockQueryRepository.deleteById(stockCode);
        return true;
    }
    
    public List<StockQuery> findAllStocksByCompanyCode(int companyCode){
        Optional<CompanyQuery> companyQueryOptional = companyQueryRepository.findById(companyCode);
        if(!companyQueryOptional.isPresent())
            return  null;
        List<StockQuery> stocksCreatedOnBetween = stockQueryRepository
                .findByCompanyQueryCompanyCode(companyCode);
        return stocksCreatedOnBetween;
    }
}
