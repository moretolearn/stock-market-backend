package com.cts.fse.stockmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.fse.stockmarket.bean.StockCreation;

@Repository
public interface StockCommandRepository extends JpaRepository<StockCreation, Integer> {
}
