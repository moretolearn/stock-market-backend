package command.service.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class CompanyCreation {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int companyCode;

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

