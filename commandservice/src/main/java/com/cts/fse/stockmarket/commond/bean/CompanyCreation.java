package com.cts.fse.stockmarket.commond.bean;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Data
public class CompanyCreation {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int companyCode;

    @NotEmpty(message = "can not be empty")
    private String companyName;

    private String description;

    private String ceo;

    private long turnover;

    private String website;

    private String exchange;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "companyCreation",
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<StockCreation> stocks;





}

