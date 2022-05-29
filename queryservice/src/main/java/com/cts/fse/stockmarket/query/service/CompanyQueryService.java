package com.cts.fse.stockmarket.query.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.stockmarket.query.bean.CompanyQuery;
import com.cts.fse.stockmarket.query.repository.CompanyQueryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyQueryService {

    @Autowired
    CompanyQueryRepository companyQueryRepository;

    public List<CompanyQuery> getAllCompanies()
    {
        List<CompanyQuery> companies = new ArrayList<CompanyQuery>();
        companyQueryRepository.findAll().forEach(company -> companies.add(company));
        return companies;
    }

    public Optional<CompanyQuery> getSingleCompanybyCompanyId(int companyCode){
        Optional<CompanyQuery> companyQueryOptional=companyQueryRepository.findById(companyCode);
       return companyQueryOptional;
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
