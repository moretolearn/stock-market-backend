package com.cts.fse.stockmarket.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class StockCreation {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int stockCode;

    private String stockName;

    private String description;

    private double price;

    private Date createdOn;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="companyCode", nullable=false)
    private CompanyCreation companyCreation;
}

