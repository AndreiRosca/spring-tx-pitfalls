package com.endava.spring.tx.pitfalls.service;

import com.endava.spring.tx.pitfalls.domain.Employee;

import java.util.List;
import java.util.Optional;

/**
 * Created by anrosca on Dec, 2017
 */
public interface EmployeeService {
    Employee create(Employee employee);

    List<Employee> findAll();

    Optional<Employee> findById(String id);

    Optional<Employee> findByDomainName(String domainName);

    void delete(String id);
}
