package command.service.bean;

import lombok.Data;

import java.util.List;
@Data
public class CompanyUpdation {

    private List<StockUpdation> stocks;
}
