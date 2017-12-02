package com.endava.spring.tx.pitfalls.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

/**
 * Created by anrosca on Dec, 2017
 */
@Configuration
@ComponentScan(basePackages = {"com.endava.spring.tx.pitfalls"})
public class AppConfiguration {
    private static final Logger LOGGER = Logger.getLogger(AppConfiguration.class);

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new LoggingTransactionManager();
    }

    private static class LoggingTransactionManager implements PlatformTransactionManager {

        @Override
        public TransactionStatus getTransaction(TransactionDefinition transactionDefinition) throws TransactionException {
            LOGGER.info("**************************************************************************");
            LOGGER.info("*                       STARTING THE TRANSACTION                         *");
            LOGGER.info("**************************************************************************");
            return new DummyTransactionStatus();
        }

        @Override
        public void commit(TransactionStatus transactionStatus) throws TransactionException {
            LOGGER.info("**************************************************************************");
            LOGGER.info("*                       COMMITTING THE TRANSACTION                       *");
            LOGGER.info("**************************************************************************");
        }

        @Override
        public void rollback(TransactionStatus transactionStatus) throws TransactionException {
            LOGGER.info("**************************************************************************");
            LOGGER.info("*                       ROLLBACKING THE TRANSACTION                      *");
            LOGGER.info("**************************************************************************");
        }
    }

    private static class DummyTransactionStatus implements TransactionStatus {
        private boolean isRollbackOnly;

        @Override
        public boolean isNewTransaction() {
            return true;
        }

        @Override
        public boolean hasSavepoint() {
            return false;
        }

        @Override
        public void setRollbackOnly() {
            isRollbackOnly = true;
        }

        @Override
        public boolean isRollbackOnly() {
            return isRollbackOnly;
        }

        @Override
        public void flush() {
        }

        @Override
        public boolean isCompleted() {
            return false;
        }

        @Override
        public Object createSavepoint() throws TransactionException {
            return null;
        }

        @Override
        public void rollbackToSavepoint(Object o) throws TransactionException {
        }

        @Override
        public void releaseSavepoint(Object o) throws TransactionException {
        }
    }
}
