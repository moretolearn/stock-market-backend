package com.cts.fse.stockmarket.commond.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.fse.stockmarket.commond.bean.StockCreation;

@Repository
public interface StockCommandRepository extends JpaRepository<StockCreation, Integer> {
}
