package com.endava.spring.tx.pitfalls.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by anrosca on Dec, 2017
 */
public class EmployeeSynchronizationService2IT extends AbstractIT {

    @Mock
    private EmployeeRestConsumerService employeeRestConsumerService;

    @Autowired
    private EmployeeSynchronizationService synchronizationService;

    @InjectMocks
    private EmployeeSynchronizationService targetSynchronizationService;

    @Before
    public void setUp() throws Exception {
        targetSynchronizationService = unwrapProxy(synchronizationService);
        MockitoAnnotations.initMocks(this);
    }

    private static <T> T unwrapProxy(T proxy) throws Exception {
        if(AopUtils.isAopProxy(proxy) && proxy instanceof Advised) {
            Object target = ((Advised) proxy).getTargetSource().getTarget();
            return (T) target;
        }
        return proxy;
    }

    @Test
    public void test() {
        synchronizationService.synchronize();
    }
}
