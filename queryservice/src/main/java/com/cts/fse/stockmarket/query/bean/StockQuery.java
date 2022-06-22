package com.cts.fse.stockmarket.query.bean;

import lombok.Data;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Document
public class StockQuery {

	@Id
	private Long stockCode;

	private String stockName;

	private String description;

	private double price;

	private Date startDate;

	private Date endDate;

//    @Temporal(TemporalType.DATE)
	private Date createdOn;

//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "company_code")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private CompanyQuery company;
}
