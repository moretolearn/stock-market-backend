package com.cts.fse.stockmarket.query.service;

import com.cts.fse.stockmarket.query.bean.CompanyQuery;
import com.cts.fse.stockmarket.query.bean.StockQuery;
import com.cts.fse.stockmarket.query.repository.CompanyQueryRepository;
import com.cts.fse.stockmarket.query.repository.StockQueryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
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
			System.err.println(companyMessage);
			CompanyQuery receivedcompanyQuery = OBJECT_MAPPER.readValue(companyMessage, CompanyQuery.class);
			Optional<CompanyQuery> singleCompanybyCompanyId = companyQueryRepository
					.findByCompanyCode(receivedcompanyQuery.getCompanyCode());
			if (singleCompanybyCompanyId.isPresent()) {
				CompanyQuery existingCompany = singleCompanybyCompanyId.get();
				existingCompany.setCeo(receivedcompanyQuery.getCeo());
				existingCompany.setCompanyName(receivedcompanyQuery.getCompanyName());
				existingCompany.setDescription(receivedcompanyQuery.getDescription());
				existingCompany.setExchange(receivedcompanyQuery.getExchange());
				existingCompany.setTurnover(receivedcompanyQuery.getTurnover());
				existingCompany.setWebsite(receivedcompanyQuery.getWebsite());
				existingCompany.setStocks(receivedcompanyQuery.getStocks());
				companyQueryRepository.save(existingCompany);
				if (existingCompany.getStocks().size() > 0) {
					existingCompany.getStocks().forEach(entity -> stockQueryRepository.save(entity));
				}
			}

			else {
				CompanyQuery newCompany = new CompanyQuery();
				newCompany.setCompanyCode(receivedcompanyQuery.getCompanyCode());
				newCompany.setCeo(receivedcompanyQuery.getCeo());
				newCompany.setCompanyName(receivedcompanyQuery.getCompanyName());
				newCompany.setDescription(receivedcompanyQuery.getDescription());
				newCompany.setExchange(receivedcompanyQuery.getExchange());
				newCompany.setTurnover(receivedcompanyQuery.getTurnover());
				newCompany.setWebsite(receivedcompanyQuery.getWebsite());
				newCompany.setStocks(receivedcompanyQuery.getStocks());
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

	@Autowired
	private MongoTemplate mongoTemplate;

	@KafkaListener(topics = "stock_market_delete1", groupId = "group_id")
	public void consume2(String companyMessage) {
		try {
			System.err.println(companyMessage);

			StockQuery stockQuery = OBJECT_MAPPER.readValue(companyMessage, StockQuery.class);

			Optional<StockQuery> singleStockbyStockCode = stockQueryRepository.findById(stockQuery.getStockCode());
			if (singleStockbyStockCode.isPresent()) {
				StockQuery stockQuery2 = singleStockbyStockCode.get();
				stockQueryRepository.deleteById(stockQuery.getStockCode());
			}
			
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