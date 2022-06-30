package com.cts.fse.stockmarket.command.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import javax.persistence.*;

import java.util.Date;

@Data
@Entity
@Table(name = "stock")
public class StockCreation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long stockCode;

    private String stockName;

    private String description;

    private double price;
    
    private Date startDate;
    
    private Date endDate;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy",timezone = "Asia/Kolkata")
//    @Temporal(TemporalType.DATE)
    private Date createdOn;

  
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="companyCode")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private CompanyCreation company;
}

