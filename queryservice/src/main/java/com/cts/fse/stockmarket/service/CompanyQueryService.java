package com.cts.fse.stockmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.stockmarket.bean.CompanyCreation;
import com.cts.fse.stockmarket.repository.CompanyQueryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyQueryService {

    @Autowired
    CompanyQueryRepository companyQueryRepository;

    public List<CompanyCreation> getAllCompanies()
    {
        List<CompanyCreation> companies = new ArrayList<CompanyCreation>();
        companyQueryRepository.findAll().forEach(company -> companies.add(company));
        return companies;
    }

    public CompanyCreation getSingleCompanybyCompanyId(int companyCode) throws  Exception{
        return companyQueryRepository
                .findById(companyCode)
                .orElseThrow(()-> new Exception("company not found with this id "+companyCode));
    }
}
