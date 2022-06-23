package com.cts.fse.stockmarket.query.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cts.fse.stockmarket.query.bean.StockQuery;
import com.cts.fse.stockmarket.query.dto.StockMinMaxAvgDto;

@Repository
public interface StockQueryRepository extends MongoRepository<StockQuery, Long>{
	
    List<StockQuery> findByCompanyCompanyCodeAndCreatedOnBetween(Long companyId, Date startDate, Date endDate);
    
    List<StockQuery> findByCompanyCompanyCode(Long companyId);
    
    List<StockQuery> findByStockCode(Long companyId);
    
//    @Query(value = "select min(price) as min,max(price) as max,avg(price) as avg from stock s where company_code = ?",nativeQuery = true)
//    StockMinMaxAvgDto findStocksByMinMaxAvg(Long companyId);
//    
//    @Query(value = "select min(price) as min,max(price) as max,avg(price) as avg from stock s where company_code = ? and (created_on between ? and ?)",nativeQuery = true)
//    StockMinMaxAvgDto findStocksByMinMaxAvgByDates(Long companyId, Date startDate, Date endDate);

}
