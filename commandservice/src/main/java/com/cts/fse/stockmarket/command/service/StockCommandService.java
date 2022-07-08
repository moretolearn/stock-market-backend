package com.cts.fse.stockmarket.command.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.stockmarket.command.bean.CompanyCreation;
import com.cts.fse.stockmarket.command.bean.StockCreation;
import com.cts.fse.stockmarket.command.repository.CompanyCommandRepository;
import com.cts.fse.stockmarket.command.repository.StockCommandRepository;

@Service
public class StockCommandService {

	@Autowired
	StockCommandRepository stockCommandRepository;

	@Autowired
	CompanyCommandRepository companyCommandRepository;

	@Autowired
	CommandProducer commandProducer;

	public StockCreation addStock(Long companyId, StockCreation stockCreation) {
		Optional<CompanyCreation> companyQueryOptional = companyCommandRepository.findById(companyId);
		if (!companyQueryOptional.isPresent())
			return null;
		stockCreation.setCompany(companyQueryOptional.get());
		stockCreation.setCreatedOn(new Date());
		StockCreation save = stockCommandRepository.save(stockCreation);

		CompanyCreation companyCreation = companyCommandRepository.findById(companyId).get();
		companyCommandRepository.refresh(companyCreation);
		RequestData requestData = new RequestData();
        requestData.setCompany(companyCreation);
        requestData.setCompanyCode(companyCreation.getCompanyCode());
        requestData.setStockCode(save.getStockCode());
        requestData.setStock(save);
        commandProducer.publishMessage(requestData,"stock_market");
		return stockCreation;
	}

	public String deleteStock(Long stockId,Long companyCode) throws Exception{
        StockCreation stockCreation= stockCommandRepository
                .findById(stockId)
                .orElseThrow(() -> new Exception("Stock not found with Id :: "+ stockId));
        stockCommandRepository.deleteById(stockId);
//        companyCommandRepository.refresh(companyCreation);
        RequestData requestData = new RequestData();
        requestData.setCompanyCode(companyCode);
        requestData.setStockCode(stockCreation.getStockCode());
        requestData.setStock(stockCreation);
        commandProducer.publishMessage(requestData,"stock_market_delete1");
      return "company deleted Successfully with id "+stockId;
  }

	public StockCreation updateStock(StockCreation stockQuery, Long companyCode, Long stockCode) {
		Optional<CompanyCreation> companyQueryOptional = companyCommandRepository.findById(companyCode);
		if (!companyQueryOptional.isPresent())
			return null;
		Optional<StockCreation> stockQueryOptional = stockCommandRepository.findById(stockCode);
		if (!stockQueryOptional.isPresent())
			return null;

		stockQuery.setCompany(companyQueryOptional.get());
		stockQuery.setStockCode(stockQueryOptional.get().getStockCode());
		stockQuery.setCreatedOn(new Date());
		StockCreation save = stockCommandRepository.save(stockQuery);

		CompanyCreation companyupdation = companyCommandRepository.findById(companyCode).get();
		companyCommandRepository.refresh(companyupdation);
		RequestData requestData = new RequestData();
        requestData.setCompany(companyupdation);
        requestData.setCompanyCode(companyupdation.getCompanyCode());
        requestData.setStockCode(save.getStockCode());
        requestData.setStock(save);
        commandProducer.publishMessage(requestData,"stock_market");
		return stockQuery;
	}
}
