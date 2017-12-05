package com.endava.spring.tx.pitfalls.service;

import com.endava.spring.tx.pitfalls.domain.Employee;

import java.util.List;

/**
 * Created by anrosca on Dec, 2017
 */
public interface EmployeeRestConsumerService {

    List<Employee> getAll();
}
