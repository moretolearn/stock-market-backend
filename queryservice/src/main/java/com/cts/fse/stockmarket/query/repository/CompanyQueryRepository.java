package com.cts.fse.stockmarket.query.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.fse.stockmarket.commond.bean.CompanyCreation;

@Repository
public interface CompanyQueryRepository extends CrudRepository<CompanyCreation, Integer> {

    }

