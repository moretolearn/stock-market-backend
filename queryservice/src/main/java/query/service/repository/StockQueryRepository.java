package query.service.repository;

import command.service.bean.StockCreation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface StockQueryRepository extends CrudRepository<StockCreation, Integer>{
}
