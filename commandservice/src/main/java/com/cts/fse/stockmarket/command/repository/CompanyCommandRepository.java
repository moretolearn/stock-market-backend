package com.cts.fse.stockmarket.command.repository;

import java.util.Optional;

import com.cts.fse.stockmarket.command.bean.CompanyCreation;

//@Repository
public interface CompanyCommandRepository extends CustomRepository<CompanyCreation, Long> {

	Optional<CompanyCreation> findByCompanyCode(Long companyCode);
}