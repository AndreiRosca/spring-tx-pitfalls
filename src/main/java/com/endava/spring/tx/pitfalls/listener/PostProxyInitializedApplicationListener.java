package com.endava.spring.tx.pitfalls.listener;

import com.endava.spring.tx.pitfalls.service.annotation.PostProxyInitialized;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by anrosca on Dec, 2017
 */
@Component
public class PostProxyInitializedApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger LOGGER = Logger.getLogger(PostProxyInitializedApplicationListener.class);

    @Autowired
    private DefaultListableBeanFactory beanFactory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOGGER.info("Invoking post proxy initialized methods.");
        try {
            tryOnApplicationEvent(event);
        } catch (Exception e) {
            LOGGER.error("Error while trying to invoke post proxy initialized methods.", e);
            throw new RuntimeException(e);
        }
    }

    private void tryOnApplicationEvent(ContextRefreshedEvent event) throws Exception {
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            String beanClassName = beanDefinition.getBeanClassName();
            if (beanClassName != null) {
                Class<?> beanClass = Class.forName(beanClassName);
                Object bean = beanFactory.getBean(beanName);
                for (Method method : beanClass.getDeclaredMethods()) {
                    if (method.isAnnotationPresent(PostProxyInitialized.class)) {
                        invokePostProxyInitializedMethod(bean, method);
                    }
                }
            }
        }
    }

    private void invokePostProxyInitializedMethod(Object bean, Method method) throws Exception {
        Class<?> proxyClass = bean.getClass();
        Method initMethod = proxyClass.getMethod(method.getName(), method.getParameterTypes());
        if (initMethod != null) {
            initMethod.invoke(bean);
        }
    }
}
