package com.cts.fse.stockmarket.query.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.fse.stockmarket.query.bean.StockQuery;

@Repository
public interface StockQueryRepository extends JpaRepository<StockQuery, Integer>{
	
    List<StockQuery> findByCompanyQueryCompanyCodeAndCreatedOnBetween(int companyId, Date startDate, Date endDate);

}
