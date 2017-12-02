package com.endava.spring.tx.pitfalls;

import com.endava.spring.tx.pitfalls.config.AppConfiguration;
import com.endava.spring.tx.pitfalls.domain.Employee;
import com.endava.spring.tx.pitfalls.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@SpringBootApplication
@EnableTransactionManagement
@Import({ AppConfiguration.class })
public class SpringTxPitfallsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTxPitfallsApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(EmployeeService service) {
		return (args) -> {
            List<Employee> employees = service.findAll();
        };
	}
}
