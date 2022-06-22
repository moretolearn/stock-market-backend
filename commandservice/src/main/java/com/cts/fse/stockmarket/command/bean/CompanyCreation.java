package com.cts.fse.stockmarket.command.bean;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import java.util.List;

@Data
@Entity
@Table(name = "company")
public class CompanyCreation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long companyCode;

    @NotEmpty(message = "can not be empty")
    private String companyName;

    @NotEmpty(message = "can not be empty")
    private String description;

    @NotEmpty(message = "can not be empty")
    private String ceo;

    @Min(value = 100000000,message = "Company Turnover must be greater than 10Cr")
    private long turnover;

    @NotEmpty(message = "can not be empty")
    private String website;

    private String exchange;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "company")
    private List<StockCreation> stocks;





}

