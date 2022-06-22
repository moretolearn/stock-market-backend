package com.cts.fse.stockmarket.query.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cts.fse.stockmarket.query.bean.CompanyQuery;

@Repository
public interface CompanyQueryRepository extends MongoRepository<CompanyQuery, Long> {
	
	Optional<CompanyQuery> findByCompanyCode(Long companyId);

    CompanyQuery findByCompanyCodeAndStocksCreatedOnBetween(Long companyId, Date startDate, Date endDate);

    }

