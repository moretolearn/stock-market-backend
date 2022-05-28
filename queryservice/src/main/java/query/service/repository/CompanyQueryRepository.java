package query.service.repository;

import command.service.bean.CompanyCreation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyQueryRepository extends CrudRepository<CompanyCreation, Integer> {

    }

