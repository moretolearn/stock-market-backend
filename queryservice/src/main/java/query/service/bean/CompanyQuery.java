package query.service.bean;

import lombok.Data;

import java.util.List;
@Data
public class CompanyQuery {
    private int companyCode;

    private String companyName;

    private String description;

    private String ceo;

    private long turnover;

    private String website;

    private String exchange;

    private List<StockQuery> stocks;





}

