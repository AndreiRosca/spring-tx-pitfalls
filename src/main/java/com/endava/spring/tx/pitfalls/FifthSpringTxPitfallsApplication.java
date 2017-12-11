package com.endava.spring.tx.pitfalls;

import com.endava.spring.tx.pitfalls.config.AppConfiguration;
import com.endava.spring.tx.pitfalls.domain.Employee;
import com.endava.spring.tx.pitfalls.repository.EmployeeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableTransactionManagement
@Import({ AppConfiguration.class })
public class FifthSpringTxPitfallsApplication {
    private static final Logger LOGGER = Logger.getLogger(FifthSpringTxPitfallsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FifthSpringTxPitfallsApplication.class, args);
	}

	@Bean
	public CommandLineRunner init() {
		return (args) -> {

        };
	}

	@Bean("employeeService")
    public EmployeeService employeeService() {
	    return new EmployeeService();
    }

    @Bean("employeeService")
    public EmployeeService employeeService1() {
        return new EmployeeService();
    }

	public static class EmployeeService {

        @PostConstruct
        public void init() {
            LOGGER.info("************************ Welcome ************************ ");
        }
    }

    @Component
    public class AppListener implements ApplicationListener<ContextRefreshedEvent> {

	    @Autowired
	    private DefaultListableBeanFactory beanFactory;

        @Override
        public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        }
    }
}
