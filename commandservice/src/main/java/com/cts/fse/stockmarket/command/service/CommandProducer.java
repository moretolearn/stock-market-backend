package com.cts.fse.stockmarket.command.service;

import com.cts.fse.stockmarket.command.bean.CompanyCreation;
import com.cts.fse.stockmarket.command.bean.StockCreation;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CommandProducer {

	@Autowired
	private KafkaTemplate<Long, String> kafkaTemplate;

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public void publishMessage(CompanyCreation companyCreation, String topic) {
		try {
			String value = OBJECT_MAPPER.writeValueAsString(companyCreation);
			log.info(String.format("#### -> Producing message -> %s", value));
//            this.kafkaTemplate.sendDefault(companyCreation.getCompanyCode(), value);
			this.kafkaTemplate.send(topic, companyCreation.getCompanyCode(), value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void publishMessage(StockCreation stockCreation, String topic) {
		try {
			String value = OBJECT_MAPPER.writeValueAsString(stockCreation);
			log.info(String.format("#### -> Producing message -> %s", value));
//            this.kafkaTemplate.sendDefault(companyCreation.getCompanyCode(), value);
			this.kafkaTemplate.send(topic, stockCreation.getStockCode(), value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}