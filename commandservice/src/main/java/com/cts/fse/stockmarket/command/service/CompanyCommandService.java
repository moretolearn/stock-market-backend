package com.cts.fse.stockmarket.command.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.stockmarket.command.bean.CompanyCreation;
import com.cts.fse.stockmarket.command.repository.CompanyCommandRepository;

import java.util.Optional;

@Service
public class CompanyCommandService {


    @Autowired
    CompanyCommandRepository companyCommandRepository;
    
    @Autowired
    CommandProducer commandProducer;

    public CompanyCreation addCompany(CompanyCreation companyQuery) {
    	companyCommandRepository.save(companyQuery);
        CompanyCreation companyCreation = companyCommandRepository.findById(companyQuery.getCompanyCode()).get();
        companyCommandRepository.refresh(companyCreation);
        commandProducer.publishMessage(companyCreation,"stock_market");
        return companyCreation;
    }

    public CompanyCreation updateCompany(CompanyCreation companyCreation, Long companyCode){
    	Optional<CompanyCreation> companyQueryOptional=companyCommandRepository.findById(companyCode);
        if(!companyQueryOptional.isPresent())
            return null;

        companyCreation.setCompanyCode(companyQueryOptional.get().getCompanyCode());
        companyCommandRepository.save(companyCreation);

        CompanyCreation companyupdation = companyCommandRepository.findById(companyCreation.getCompanyCode()).get();
        companyCommandRepository.refresh(companyupdation);
        commandProducer.publishMessage(companyupdation,"stock_market");
        return companyupdation;

    }
    
    public String deleteCompany(Long companyId) throws Exception{
          CompanyCreation companyCreation= companyCommandRepository
                  .findById(companyId)
                  .orElseThrow(() -> new Exception("Company not found with Id :: "+ companyId));
          companyCommandRepository.delete(companyCreation);
//          companyCommandRepository.refresh(companyCreation);
          commandProducer.publishMessage(companyCreation,"stock_market_delete");
        return "company deleted Successfully with id "+companyId;
    }

   
}
