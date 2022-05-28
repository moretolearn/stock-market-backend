package command.service.repository;

import command.service.bean.CompanyCreation;
import command.service.bean.StockCreation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CompanyCommandRepository extends JpaRepository<CompanyCreation, Integer> {

    List<StockCreation> findByCompanyCodeAndStocksCreatedOnBetween(int companyId, Date startDate, Date endDate);

}