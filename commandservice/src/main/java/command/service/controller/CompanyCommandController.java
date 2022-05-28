package command.service.controller;


import command.service.bean.CompanyCreation;
import command.service.bean.StockCreation;
import command.service.service.CompanyCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyCommandController<T> {

    @Autowired
    CompanyCommandService companyService;

    @PostMapping("/add")
    public ResponseEntity<String> addCompany(@RequestBody CompanyCreation company) {

        companyService.addCompany(company);
        return new ResponseEntity<>("Company Added Successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable(value = "id") int companyId)
            throws Exception {
        return new ResponseEntity<>(companyService.deleteCompany(companyId),HttpStatus.OK);
    }

    @GetMapping("/get/{companyCode}/{startDate}/{endDate}")
    public ResponseEntity<List<StockCreation>> findAllStocksBetweenDates(@PathVariable(value = "companyCode") int companyCode, @PathVariable(value = "startDate") Date startDate,
                                                                         @PathVariable(value = "endDate") Date endDate){
        return new ResponseEntity<List<StockCreation>>(companyService.findAllStocksBetweenDates(companyCode, startDate, endDate),HttpStatus.OK);
    }
}