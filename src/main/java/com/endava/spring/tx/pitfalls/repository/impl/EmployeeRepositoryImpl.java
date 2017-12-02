package com.endava.spring.tx.pitfalls.repository.impl;

import com.endava.spring.tx.pitfalls.domain.Employee;
import com.endava.spring.tx.pitfalls.repository.EmployeeRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Created by anrosca on Dec, 2017
 */
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
    private static final AtomicLong idGenerator = new AtomicLong();
    private static final Map<String, Employee> employees = new ConcurrentHashMap<>();

    @Override
    public Employee create(Employee employee) {
        employee.setId(Long.toString(idGenerator.incrementAndGet()));
        employees.put(employee.getId(), employee);
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        return employees.values()
                .stream()
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Employee> findById(String id) {
        return employees.values()
                .stream()
                .filter(e -> e.getId() != null && e.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    @Override
    public Optional<Employee> findByDomainName(String domainName) {
        return employees.values()
                .stream()
                .filter(e -> e.getDomainName().equalsIgnoreCase(domainName))
                .findFirst();
    }

    @Override
    public void delete(String id) {
        employees.remove(id);
    }
}
