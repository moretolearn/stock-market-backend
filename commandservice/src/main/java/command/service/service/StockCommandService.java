package command.service.service;

import command.service.bean.StockCreation;
import command.service.repository.StockCommandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockCommandService {

    @Autowired
    StockCommandRepository stockCommandRepository;

    public void addStock(StockCreation stock) {
        stockCommandRepository.save(stock);
    }
}
