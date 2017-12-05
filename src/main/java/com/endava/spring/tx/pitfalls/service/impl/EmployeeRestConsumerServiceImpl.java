package com.endava.spring.tx.pitfalls.service.impl;

import com.endava.spring.tx.pitfalls.domain.Employee;
import com.endava.spring.tx.pitfalls.service.EmployeeRestConsumerService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by anrosca on Dec, 2017
 */
@Service
public class EmployeeRestConsumerServiceImpl implements EmployeeRestConsumerService {
    private static final Logger LOGGER = Logger.getLogger(EmployeeRestConsumerServiceImpl.class);

    @Override
    public List<Employee> getAll() {
        LOGGER.info("Consuming the employee rest service.");
        return Arrays.asList(
                Employee.newBuilder()
                   .setDomainName("anrosca")
                   .setFirstName("Andrei")
                   .setLastName("Rosca")
                   .setEmail("Andrei.Rosca@endava.com")
                   .build(),
                Employee.newBuilder()
                        .setDomainName("dritchie")
                        .setFirstName("Denis")
                        .setLastName("Ritchie")
                        .setEmail("Denis.Ritchie@endava.com")
                        .build()
        );
    }
}
