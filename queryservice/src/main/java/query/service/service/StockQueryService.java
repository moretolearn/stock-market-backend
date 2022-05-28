package query.service.service;

import command.service.bean.CompanyCreation;
import command.service.bean.StockCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import query.service.bean.StockQuery;
import query.service.repository.StockQueryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StockQueryService {

    @Autowired
    StockQueryRepository stockQueryRepository;

    public List<StockCreation> getAllStocks()
    {
        List<StockCreation> stocks = new ArrayList<StockCreation>();
        stockQueryRepository.findAll().forEach(stock -> stocks.add(stock));
        return stocks;
    }

    public StockCreation getSingleStockbyId(int stockId) throws Exception{
        return stockQueryRepository
                .findById(stockId)
                .orElseThrow(()-> new Exception("stock not found with this id "+stockId));
    }
}
