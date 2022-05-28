package command.service.service;

import command.service.bean.CompanyCreation;
import command.service.bean.StockCreation;
import command.service.repository.CompanyCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CompanyCommandService {


    @Autowired
    CompanyCommandRepository companyRepository;

    public void addCompany(CompanyCreation company) {
        if (!companyRepository.existsById(Integer.valueOf(company.getCompanyCode())))
        companyRepository.save(company);
    }

    public void updateCompany(CompanyCreation company) {

       if (companyRepository.existsById(Integer.valueOf(company.getCompanyCode())))
           companyRepository.save(company);


  }

    public String deleteCompany(int companyId) throws Exception{
          CompanyCreation companyCreation= companyRepository
                  .findById(companyId)
                  .orElseThrow(() -> new Exception("Company not found with Id :: "+ companyId));
        companyRepository.delete(companyCreation);
        return "company deleted Successfully with id "+companyId;
    }

    public List<StockCreation> findAllStocksBetweenDates(int companyCode, Date startDate, Date endDate){
        List<StockCreation> stocksCreatedOnBetween = companyRepository
                .findByCompanyCodeAndStocksCreatedOnBetween(companyCode, startDate, endDate);
        return stocksCreatedOnBetween;
    }
}
