package command.service.repository;

import command.service.bean.StockCreation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockCommandRepository extends JpaRepository<StockCreation, Integer> {
}
