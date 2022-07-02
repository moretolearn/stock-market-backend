package com.cts.fse.stockmarket.query.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.stockmarket.query.bean.CompanyQuery;
import com.cts.fse.stockmarket.query.bean.StockQuery;
import com.cts.fse.stockmarket.query.dto.StocksMinMaxAvgDto;
import com.cts.fse.stockmarket.query.repository.CompanyQueryRepository;
import com.cts.fse.stockmarket.query.repository.StockQueryRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyQueryService {

    @Autowired
    CompanyQueryRepository companyQueryRepository;
    
    @Autowired
    StockQueryRepository stockQueryRepository;

    public List<CompanyQuery> getAllCompanies()
    {
    	List<CompanyQuery> findAll = companyQueryRepository.findAll();
        return findAll;
    }

    public StocksMinMaxAvgDto<Object> getSingleCompanybyCompanyId(Long companyCode){
        Optional<CompanyQuery> companyQueryOptional=companyQueryRepository.findByCompanyCode(companyCode);
//        StockMinMaxAvgDto findStocksByMinMaxAvg = stockQueryRepository.findStocksByMinMaxAvg(companyCode);
        StocksMinMaxAvgDto<Object> stocksMinMaxAvgDto = new StocksMinMaxAvgDto<Object>();
        if(companyQueryOptional.isPresent())
        stocksMinMaxAvgDto.setObject(companyQueryOptional.get());
//        stocksMinMaxAvgDto.setStockMinMaxAvgDto(findStocksByMinMaxAvg);
       return stocksMinMaxAvgDto;
    }
    
    public Optional<CompanyQuery> getSingleCompanybyCompanyId1(Long companyCode){
        Optional<CompanyQuery> companyQueryOptional=companyQueryRepository.findById(companyCode);
       return companyQueryOptional;
    }
    
    public CompanyQuery createCompany(CompanyQuery companyQuery){
        CompanyQuery savedCompanyQuery = companyQueryRepository.save(companyQuery);
        return savedCompanyQuery;
    }
    
    public List<StockQuery> findAllStocksBetweenDates1(Long companyCode, Date startDate, Date endDate){
        Optional<CompanyQuery> companyQueryOptional = companyQueryRepository.findById(companyCode);
        if(!companyQueryOptional.isPresent())
            return  null;
        CompanyQuery byCompanyCodeAndStocksCreatedOnBetween = companyQueryRepository
                .findByCompanyCodeAndStocksCreatedOnBetween(companyCode, startDate, endDate);
        if(byCompanyCodeAndStocksCreatedOnBetween!=null)
            return byCompanyCodeAndStocksCreatedOnBetween.getStocks();
        else
            return null;
    }
    
    public List<StockQuery> findAllStocksBetweenDates(Long companyCode, Date startDate, Date endDate){
        Optional<CompanyQuery> companyQueryOptional = companyQueryRepository.findById(companyCode);
        if(!companyQueryOptional.isPresent())
            return  null;
        CompanyQuery byCompanyCodeAndStocksCreatedOnBetween = companyQueryRepository
                .findByCompanyCodeAndStocksCreatedOnBetween(companyCode, startDate, endDate);
        if(byCompanyCodeAndStocksCreatedOnBetween!=null)
            return byCompanyCodeAndStocksCreatedOnBetween.getStocks();
        else
            return null;
    }

    

    public boolean deleteCompany(Long companyCode){
        Optional<CompanyQuery> companyQueryOptional=companyQueryRepository.findById(companyCode);
        if(!companyQueryOptional.isPresent())
           return false;
        companyQueryRepository.deleteById(companyCode);
            return true;

    }

}
