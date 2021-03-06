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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableTransactionManagement
@Import({ AppConfiguration.class })
public class FourthSpringTxPitfallsApplication {
    private static final Logger LOGGER = Logger.getLogger(FourthSpringTxPitfallsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FourthSpringTxPitfallsApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(BatchPersistEmployeeService service) {
		return (args) -> {
            service.batchCreate(Arrays.asList(Employee.newBuilder()
                            .setDomainName("anrosca")
                            .setFirstName("Andrei")
                            .setLastName("Rosca")
                            .setEmail("Andrei.Rosca@endava.com")
                            .build(),
                    Employee.newBuilder()
                            .setDomainName("eracila")
                            .setFirstName("Evghenii")
                            .setLastName("Racila")
                            .setEmail("Evghenii.Racila@endava.com")
                            .build()));
        };
	}

	@Service
	public static class BatchPersistEmployeeService implements Serializable, Comparable<BatchPersistEmployeeService> {

        @Autowired
        private EmployeeRepository employeeRepository;

        @Transactional
        public Employee create(Employee employee) {
            LOGGER.info("Creating a new employee");
            return employeeRepository.create(employee);
        }

        @Transactional
        public Collection<Employee> batchCreate(Collection<Employee> employees) {
            LOGGER.info("Batch creating users.");
            return employees.stream()
                    .map(this::create)
                    .collect(Collectors.toCollection(ArrayList::new));
        }

        @Override
        public int compareTo(BatchPersistEmployeeService o) {
            return 0;
        }
    }
}
