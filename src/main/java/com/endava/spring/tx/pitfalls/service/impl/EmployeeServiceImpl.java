package com.endava.spring.tx.pitfalls.service.impl;

import com.endava.spring.tx.pitfalls.domain.Employee;
import com.endava.spring.tx.pitfalls.repository.EmployeeRepository;
import com.endava.spring.tx.pitfalls.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by anrosca on Dec, 2017
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    @Override
    public Employee create(Employee employee) {
        return employeeRepository.create(employee);
    }

    @Transactional
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Employee> findById(String id) {
        return employeeRepository.findById(id);
    }

    @Transactional
    @Override
    public Optional<Employee> findByDomainName(String domainName) {
        return employeeRepository.findByDomainName(domainName);
    }

    @Transactional
    @Override
    public void delete(String id) {
        employeeRepository.delete(id);
    }
}
