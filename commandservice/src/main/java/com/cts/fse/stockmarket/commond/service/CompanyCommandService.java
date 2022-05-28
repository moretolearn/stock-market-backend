package com.cts.fse.stockmarket.commond.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.stockmarket.commond.bean.CompanyCreation;
import com.cts.fse.stockmarket.commond.bean.StockCreation;
import com.cts.fse.stockmarket.commond.repository.CompanyCommandRepository;

import java.util.Date;
import java.util.List;

@Service
public class CompanyCommandService {


    @Autowired
    CompanyCommandRepository companyRepository;

    public CompanyCreation addCompany(CompanyCreation company) {
        if (!companyRepository.existsById(Integer.valueOf(company.getCompanyCode())))
        companyRepository.save(company);
        return company;
    }

    public void updateCompany(CompanyCreation company) {

       if (companyRepository.existsById(Integer.valueOf(company.getCompanyCode())))
           companyRepository.save(company);


  }

    public String deleteCompany(int companyId) throws Exception{
          CompanyCreation companyCreation= companyRepository
                  .findById(companyId)
                  .orElseThrow(() -> new Exception("Company not found with Id :: "+ companyId));
        companyRepository.delete(companyCreation);
        return "company deleted Successfully with id "+companyId;
    }

    public List<StockCreation> findAllStocksBetweenDates(int companyCode, Date startDate, Date endDate){
        List<StockCreation> stocksCreatedOnBetween = companyRepository
                .findByCompanyCodeAndStocksCreatedOnBetween(companyCode, startDate, endDate);
        return stocksCreatedOnBetween;
    }
}
