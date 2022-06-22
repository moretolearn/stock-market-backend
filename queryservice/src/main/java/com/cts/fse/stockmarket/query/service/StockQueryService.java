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

    public Optional<StockQuery> getSingleStockbyId(Long stockId) throws Exception{
        return stockQueryRepository.findById(stockId);
    }
    
//    public StocksMinMaxAvgDto<?> findAllStocksBetweenDates(Long companyCode, Date startDate, Date endDate){
//        Optional<CompanyQuery> companyQueryOptional = companyQueryRepository.findById(companyCode);
//        if(!companyQueryOptional.isPresent())
//            return  null;
//        List<StockQuery> stocksCreatedOnBetween = stockQueryRepository
//                .findByCompanyQueryCompanyCodeAndCreatedOnBetween(companyCode, startDate, endDate);
//        StockMinMaxAvgDto findStocksByMinMaxAvgByDates = stockQueryRepository.findStocksByMinMaxAvgByDates(companyCode, startDate, endDate);
//        StocksMinMaxAvgDto<?> stocksMinMaxAvgDto = new StocksMinMaxAvgDto();
//        stocksMinMaxAvgDto.setObject(stocksCreatedOnBetween);
//        stocksMinMaxAvgDto.setStockMinMaxAvgDto(findStocksByMinMaxAvgByDates);
//        return stocksMinMaxAvgDto;
//    }
    
    public List<StockQuery> findAllStocksByCompanyCode(Long companyCode){
        Optional<CompanyQuery> companyQueryOptional = companyQueryRepository.findById(companyCode);
        if(!companyQueryOptional.isPresent())
            return  null;
        List<StockQuery> stocksCreatedOnBetween = stockQueryRepository
                .findByCompanyCompanyCode(companyCode);
        return stocksCreatedOnBetween;
    }
}
