package com.cts.fse.stockmarket.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.fse.stockmarket.bean.StockCreation;
@Repository
public interface StockQueryRepository extends CrudRepository<StockCreation, Integer>{
}
