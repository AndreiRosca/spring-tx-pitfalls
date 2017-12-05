package com.endava.spring.tx.pitfalls.service.impl;

import com.endava.spring.tx.pitfalls.domain.Employee;
import com.endava.spring.tx.pitfalls.service.EmployeeRestConsumerService;
import com.endava.spring.tx.pitfalls.service.EmployeeService;
import com.endava.spring.tx.pitfalls.service.EmployeeSynchronizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by anrosca on Dec, 2017
 */
@Service
public class EmployeeSynchronizationServiceImpl implements EmployeeSynchronizationService {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRestConsumerService employeeRestConsumerService;

    @Transactional
    @Override
    public void synchronize() {
        List<Employee> restEmployees = employeeRestConsumerService.getAll();
        List<Employee> employees = employeeService.findAll();
        restEmployees.removeAll(employees);
        employeeService.batchCreate(restEmployees);
    }
}
