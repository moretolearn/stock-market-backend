package com.cts.fse.stockmarket.query.bean;

import lombok.Data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Data
@Entity
@Table(name = "companyquery")
public class CompanyQuery {
	
	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int companyCode;

    private String companyName;

    private String description;

    private String ceo;

    private long turnover;

    private String website;

    private String exchange;

    @OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "companyQuery")
    private List<StockQuery> stocks;





}

