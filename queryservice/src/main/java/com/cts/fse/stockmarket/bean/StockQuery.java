package com.cts.fse.stockmarket.bean;

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
