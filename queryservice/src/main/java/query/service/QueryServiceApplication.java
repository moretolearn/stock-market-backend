package query.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EntityScan("command.service.bean")
public class QueryServiceApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(QueryServiceApplication.class, args);
    }
}