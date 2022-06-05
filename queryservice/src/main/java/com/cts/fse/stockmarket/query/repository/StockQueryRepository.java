package com.cts.fse.stockmarket.query.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.fse.stockmarket.query.bean.StockQuery;
import com.cts.fse.stockmarket.query.dto.StockMinMaxAvgDto;

@Repository
public interface StockQueryRepository extends JpaRepository<StockQuery, Integer>{
	
    List<StockQuery> findByCompanyQueryCompanyCodeAndCreatedOnBetween(int companyId, Date startDate, Date endDate);
    
    List<StockQuery> findByCompanyQueryCompanyCode(int companyId);
    
    @Query(value = "select min(price) as min,max(price) as max,avg(price) as avg from stock s where company_code = ?",nativeQuery = true)
    StockMinMaxAvgDto findStocksByMinMaxAvg(int companyId);
    
    @Query(value = "select min(price) as min,max(price) as max,avg(price) as avg from stock s where company_code = ? and (created_on between ? and ?)",nativeQuery = true)
    StockMinMaxAvgDto findStocksByMinMaxAvgByDates(int companyId, Date startDate, Date endDate);

}
