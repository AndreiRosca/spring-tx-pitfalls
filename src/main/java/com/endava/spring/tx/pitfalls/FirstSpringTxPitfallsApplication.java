package com.endava.spring.tx.pitfalls;

import com.endava.spring.tx.pitfalls.config.AppConfiguration;
import com.endava.spring.tx.pitfalls.domain.Employee;
import com.endava.spring.tx.pitfalls.repository.EmployeeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableTransactionManagement
@Import({AppConfiguration.class})
public class FirstSpringTxPitfallsApplication {
    private static final Logger LOGGER = Logger.getLogger(FirstSpringTxPitfallsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringTxPitfallsApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(SimpleEmployeeService service) {
		return (args) -> {
            service.findAll();
//            service.findEmployeesWithNameStartingWith("a");
        };
	}

	@Bean
    public SimpleEmployeeService employeeService() {
	    return new SimpleEmployeeService();
    }

    private static class SimpleEmployeeService {

	    @Autowired
	    private EmployeeRepository employeeRepository;

	    @Transactional
	    List<Employee> findEmployeesWithNameStartingWith(String namePrefix) {
//	    public List<Employee> findEmployeesWithNameStartingWith(String namePrefix) {
	        LOGGER.info("Finding employees by name prefix.");
	        return this.findAll().stream()
                    .filter(e -> e.getFirstName().startsWith(namePrefix))
                    .collect(Collectors.toList());
        }

        @Transactional(propagation = Propagation.REQUIRED)
        public List<Employee> findAll() {
	        LOGGER.info("Finding all employees");
            return employeeRepository.findAll();
        }
    }
}
