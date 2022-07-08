package com.cts.fse.stockmarket.query.service;

import com.cts.fse.stockmarket.query.bean.CompanyQuery;
import com.cts.fse.stockmarket.query.bean.StockQuery;
import com.cts.fse.stockmarket.query.repository.CompanyQueryRepository;
import com.cts.fse.stockmarket.query.repository.StockQueryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@Log4j2
public class QueryConsumer {

	@Autowired
	CompanyQueryService companyQueryService;

	@Autowired
	CompanyQueryRepository companyQueryRepository;

	@Autowired
	StockQueryRepository stockQueryRepository;

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@KafkaListener(topics = "stock_market", groupId = "group_id")
	public void consume1(String companyMessage) {
		try {
			System.out.println(companyMessage);
			ResponsData receivedcompanyQuery = OBJECT_MAPPER.readValue(companyMessage, ResponsData.class);
			System.err.println(receivedcompanyQuery);
			Optional<CompanyQuery> singleCompanybyCompanyId = companyQueryRepository
					.findByCompanyCode(receivedcompanyQuery.getCompany().getCompanyCode());
			CompanyQuery company = receivedcompanyQuery.getCompany();
			if (singleCompanybyCompanyId.isPresent()) {
				CompanyQuery existingCompany = singleCompanybyCompanyId.get();
				existingCompany.setCeo(company.getCeo());
				existingCompany.setCompanyName(company.getCompanyName());
				existingCompany.setDescription(company.getDescription());
				existingCompany.setExchange(company.getExchange());
				existingCompany.setTurnover(company.getTurnover());
				existingCompany.setWebsite(company.getWebsite());
				existingCompany.setStocks(company.getStocks());
				companyQueryRepository.save(existingCompany);
				if (existingCompany.getStocks().size() > 0) {
					existingCompany.getStocks().forEach(entity -> stockQueryRepository.save(entity));
				}
			}

			else {
				CompanyQuery newCompany = new CompanyQuery();
				newCompany.setCompanyCode(company.getCompanyCode());
				newCompany.setCeo(company.getCeo());
				newCompany.setCompanyName(company.getCompanyName());
				newCompany.setDescription(company.getDescription());
				newCompany.setExchange(company.getExchange());
				newCompany.setTurnover(company.getTurnover());
				newCompany.setWebsite(company.getWebsite());
				newCompany.setStocks(company.getStocks());
				companyQueryRepository.save(newCompany);
				if (newCompany.getStocks().size() > 0) {
					newCompany.getStocks().forEach(entity -> stockQueryRepository.save(entity));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@KafkaListener(topics = "stock_market_delete", groupId = "group_id")
	public void consume(String companyMessage) {
		try {
			System.err.println(companyMessage);
			CompanyQuery receivedcompanyQuery = OBJECT_MAPPER.readValue(companyMessage, CompanyQuery.class);
			Optional<CompanyQuery> singleCompanybyCompanyId = companyQueryRepository
					.findById(receivedcompanyQuery.getCompanyCode());
			if (singleCompanybyCompanyId.isPresent()) {
				CompanyQuery companyQuery = singleCompanybyCompanyId.get();
				companyQueryRepository.deleteById(receivedcompanyQuery.getCompanyCode());
				if (companyQuery.getStocks().size() > 0) {
					companyQuery.getStocks().forEach(entity -> stockQueryRepository.delete(entity));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@KafkaListener(topics = "stock_market_delete1", groupId = "group_id")
	public void consume2(String companyMessage) {
		try {
			System.out.println(companyMessage);

			ResponsData responsData = OBJECT_MAPPER.readValue(companyMessage, ResponsData.class);

			Optional<StockQuery> singleStockbyStockCode = stockQueryRepository.findById(responsData.getStockCode());
			if (singleStockbyStockCode.isPresent()) {
				singleStockbyStockCode.get();
				stockQueryRepository.deleteById(responsData.getStockCode());
			}
			
			List<StockQuery> findAll = stockQueryRepository.findAll();
			Optional<CompanyQuery> findById = companyQueryRepository.findById(responsData.getCompanyCode());
			findById.get().setStocks(findAll);
			companyQueryRepository.save(findById.get());
			
//			List<StockQuery> findByStockCode = stockQueryRepository.findByStockCode(stockQuery.getStockCode());
//			
//			Query query = new Query();
//			query.addCriteria(Criteria.where("stocks.stockCode").exists(true));
//			List<CompanyQuery> companyQueries = mongoTemplate.find(query, CompanyQuery.class);
//			System.err.println(companyQueries);
//			companyQueries.forEach(company -> {
//				System.err.println(company);
//				company.setStocks(new ArrayList<>());
//				companyQueryRepository.save(company);
//			});

			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}