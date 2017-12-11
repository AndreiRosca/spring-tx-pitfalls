package com.endava.spring.tx.pitfalls.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by anrosca on Dec, 2017
 */
public class EmployeeSynchronizationService1IT extends AbstractIT {

    @Mock
    private EmployeeRestConsumerService employeeRestConsumerService;

    @InjectMocks
    @Autowired
    private EmployeeSynchronizationService synchronizationService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test() {
        synchronizationService.synchronize();
    }
}
