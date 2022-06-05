package com.cts.fse.stockmarket.query.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.stockmarket.query.bean.CompanyQuery;
import com.cts.fse.stockmarket.query.dto.StockMinMaxAvgDto;
import com.cts.fse.stockmarket.query.dto.StocksMinMaxAvgDto;
import com.cts.fse.stockmarket.query.repository.CompanyQueryRepository;
import com.cts.fse.stockmarket.query.repository.StockQueryRepository;

import java.util.ArrayList;
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

    public StocksMinMaxAvgDto<Object> getSingleCompanybyCompanyId(int companyCode){
        Optional<CompanyQuery> companyQueryOptional=companyQueryRepository.findById(companyCode);
        StockMinMaxAvgDto findStocksByMinMaxAvg = stockQueryRepository.findStocksByMinMaxAvg(companyCode);
        StocksMinMaxAvgDto<Object> stocksMinMaxAvgDto = new StocksMinMaxAvgDto<Object>();
        stocksMinMaxAvgDto.setObject(companyQueryOptional.get());
        stocksMinMaxAvgDto.setStockMinMaxAvgDto(findStocksByMinMaxAvg);
       return stocksMinMaxAvgDto;
    }
    
    public CompanyQuery createCompany(CompanyQuery companyQuery){
        CompanyQuery savedCompanyQuery = companyQueryRepository.save(companyQuery);
        return savedCompanyQuery;
    }

    public CompanyQuery updateCompany(CompanyQuery companyQuery, int companyCode){
        Optional<CompanyQuery> companyQueryOptional=companyQueryRepository.findById(companyCode);
        if(!companyQueryOptional.isPresent())
            return null;

        companyQuery.setCompanyCode(companyQueryOptional.get().getCompanyCode());
        companyQueryRepository.save(companyQuery);
        return companyQuery;

    }

    public boolean deleteCompany(int companyCode){
        Optional<CompanyQuery> companyQueryOptional=companyQueryRepository.findById(companyCode);
        if(!companyQueryOptional.isPresent())
           return false;
        companyQueryRepository.deleteById(companyCode);
            return true;

    }

}
