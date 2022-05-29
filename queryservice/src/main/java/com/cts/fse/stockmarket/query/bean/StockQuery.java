package com.cts.fse.stockmarket.query.bean;

import lombok.Data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@Entity
@Table(name = "stockquery")
public class StockQuery {

	@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int stockCode;

    private String stockName;

    private String description;

    private double price;

//    @Temporal(TemporalType.DATE)
    private Date createdOn;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="companyquery_company_code")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CompanyQuery companyQuery;
}
