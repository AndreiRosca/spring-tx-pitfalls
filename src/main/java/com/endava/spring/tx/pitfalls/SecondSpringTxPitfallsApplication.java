package com.endava.spring.tx.pitfalls;

import com.endava.spring.tx.pitfalls.config.AppConfiguration;
import com.endava.spring.tx.pitfalls.domain.Employee;
import com.endava.spring.tx.pitfalls.repository.EmployeeRepository;
import com.endava.spring.tx.pitfalls.service.annotation.PostProxyInitialized;
import javafx.geometry.Pos;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

@SpringBootApplication
@EnableTransactionManagement
@Import({ AppConfiguration.class })
public class SecondSpringTxPitfallsApplication {
    private static final Logger LOGGER = Logger.getLogger(SecondSpringTxPitfallsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SecondSpringTxPitfallsApplication.class, args);
	}

	@Bean
	public CommandLineRunner init(InitMethodEmployeeService service) {
		return (args) -> {
        };
	}

	@Service
	public static class InitMethodEmployeeService {

        @Autowired
        private EmployeeRepository employeeRepository;

        @Transactional
        public Employee create(Employee employee) {
            LOGGER.info("Creating a new employee");
            return employeeRepository.create(employee);
        }

        @PostConstruct
        public void init() {
            LOGGER.info("Creating a new employee in PostConstruct");
            create(Employee.newBuilder()
                    .setDomainName("anrosca")
                    .setFirstName("Andrei")
                    .setLastName("Rosca")
                    .setEmail("Andrei.Rosca@endava.com")
                    .build());
        }

        //Solution 1
//        @Autowired
//        private PlatformTransactionManager transactionManager;
//
//        @PostConstruct
//        public void init() {
//            TransactionTemplate template = new TransactionTemplate(transactionManager);
//            template.execute(new TransactionCallbackWithoutResult() {
//                @Override
//                protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
//                    LOGGER.info("Creating a new employee in PostConstruct");
//                    create(Employee.newBuilder()
//                            .setDomainName("anrosca")
//                            .setFirstName("Andrei")
//                            .setLastName("Rosca")
//                            .setEmail("Andrei.Rosca@endava.com")
//                            .build());
//                }
//            });
//        }


        //Solution 2
//        @PostProxyInitialized
//        public void init() {
//            LOGGER.info("Creating a new employee in PostConstruct");
//            create(Employee.newBuilder()
//                    .setDomainName("anrosca")
//                    .setFirstName("Andrei")
//                    .setLastName("Rosca")
//                    .setEmail("Andrei.Rosca@endava.com")
//                    .build());
//        }

//        @Component
//        public static class PostProxyInitializedApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
//
//            @Autowired
//            private DefaultListableBeanFactory beanFactory;
//
//            @Override
//            public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//                try {
//                    String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
//                    for (String beanName : beanDefinitionNames) {
//                        BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
//                        String beanClassName = beanDefinition.getBeanClassName();
//                        if (beanClassName != null) {
//                            Class<?> beanClass = Class.forName(beanClassName);
//                            for (Method method : beanClass.getDeclaredMethods()) {
//                                if (method.isAnnotationPresent(PostProxyInitialized.class))
//                                    invokeInitMethod(beanFactory.getBean(beanName), method);
//                            }
//                        }
//                    }
//                } catch (Exception e) {
//                    LOGGER.error("", e);
//                }
//            }
//
//            private void invokeInitMethod(Object bean, Method method) throws Exception {
//                Method initMethod = bean.getClass().getMethod(method.getName());
//                initMethod.invoke(bean);
//            }
//        }
    }
}
