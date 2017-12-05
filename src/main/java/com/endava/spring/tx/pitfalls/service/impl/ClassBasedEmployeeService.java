package com.endava.spring.tx.pitfalls.service.impl;

import com.endava.spring.tx.pitfalls.domain.Employee;
import com.endava.spring.tx.pitfalls.repository.EmployeeRepository;
import com.endava.spring.tx.pitfalls.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by anrosca on Dec, 2017
 */
@Service
public class ClassBasedEmployeeService implements Serializable, Cloneable {
    private static final Logger LOGGER = Logger.getLogger(ClassBasedEmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

// for 1
// @Transactional(propagation = Propagation.REQUIRES_NEW)
    //for 1.1
    @Transactional(propagation = Propagation.REQUIRED)
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

    @Transactional
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional
    public Optional<Employee> findById(String id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    public Optional<Employee> findByDomainName(String domainName) {
        return employeeRepository.findByDomainName(domainName);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(String id) {
        employeeRepository.delete(id);
    }

    public Collection<Employee> createEmployeesFromExcelFile(InputStream inputStream) {
        Collection<Employee> employees = readExcelFile(inputStream);
//        return batchCreate(employees); pitfall
        return batchCreate(employees);
    }

    private Collection<Employee> readExcelFile(InputStream inputStream) {
        return Arrays.asList(Employee.newBuilder()
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
                        .build());
    }
}
