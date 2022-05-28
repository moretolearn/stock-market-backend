package com.cts.fse.stockmarket.query.bean;

import lombok.Data;

import java.util.Date;

@Data
public class StockQuery {

    private int stockCode;

    private String stockName;

    private String description;

    private double price;

    private Date createdOn;

    private CompanyQuery companyQuery;
}
