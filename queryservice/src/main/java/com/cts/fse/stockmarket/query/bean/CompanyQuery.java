package com.cts.fse.stockmarket.query.bean;

import lombok.Data;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CompanyQuery {
	
	@Id
    private Long companyCode;

    private String companyName;

    private String description;

    private String ceo;

    private long turnover;

    private String website;

    private String exchange;

//    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "companyQuery")
    private List<StockQuery> stocks;





}

