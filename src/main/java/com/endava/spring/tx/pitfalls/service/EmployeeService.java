package com.endava.spring.tx.pitfalls.service;

import com.endava.spring.tx.pitfalls.domain.Employee;
import com.endava.spring.tx.pitfalls.service.annotation.PostProxyInitialized;

import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by anrosca on Dec, 2017
 */
public interface EmployeeService {
    //    @PostConstruct
        @PostProxyInitialized
        default public void init() {

        }

    Employee create(Employee employee);

    Collection<Employee> batchCreate(Collection<Employee> employees);

    List<Employee> findAll();

    Optional<Employee> findById(String id);

    Optional<Employee> findByDomainName(String domainName);

    void delete(String id);

    Collection<Employee> createEmployeesFromExcelFile(InputStream inputStream);
}
