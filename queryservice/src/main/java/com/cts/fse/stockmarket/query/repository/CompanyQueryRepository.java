package com.cts.fse.stockmarket.query.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.fse.stockmarket.query.bean.CompanyQuery;

@Repository
public interface CompanyQueryRepository extends JpaRepository<CompanyQuery, Integer> {

    }

