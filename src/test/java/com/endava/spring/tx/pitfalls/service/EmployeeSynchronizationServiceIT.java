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
public class EmployeeSynchronizationServiceIT extends AbstractIT {

    @Autowired
    private EmployeeSynchronizationService synchronizationService;

    private class ProxyTargetHolder {
        @Mock
        private EmployeeRestConsumerService employeeRestConsumerService;

        @InjectMocks
        private EmployeeSynchronizationService targetSynchronizationService;
    }

    private ProxyTargetHolder proxyTargetHolder = new ProxyTargetHolder();

    @Before
    public void setUp() throws Exception {
        proxyTargetHolder.targetSynchronizationService = unwrapProxy(synchronizationService);
        MockitoAnnotations.initMocks(proxyTargetHolder);
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
