package com.cts.fse.stockmarket.commond.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class StockCreation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int stockCode;

    private String stockName;

    private String description;

    private double price;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss",timezone = "Asia/Kolkata")
    @Temporal(TemporalType.DATE)
    private Date createdOn;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="companyCode", nullable=true)
    private CompanyCreation companyCreation;
}

