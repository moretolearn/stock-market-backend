package com.cts.fse.stockmarket.command.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.fse.stockmarket.command.bean.StockCreation;

@Repository
public interface StockCommandRepository extends JpaRepository<StockCreation, Long> {
}
