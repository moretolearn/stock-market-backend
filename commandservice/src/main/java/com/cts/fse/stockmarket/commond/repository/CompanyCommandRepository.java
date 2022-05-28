package com.cts.fse.stockmarket.commond.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.fse.stockmarket.commond.bean.CompanyCreation;
import com.cts.fse.stockmarket.commond.bean.StockCreation;

import java.util.Date;
import java.util.List;

public interface CompanyCommandRepository extends JpaRepository<CompanyCreation, Integer> {

    List<StockCreation> findByCompanyCodeAndStocksCreatedOnBetween(int companyId, Date startDate, Date endDate);

}