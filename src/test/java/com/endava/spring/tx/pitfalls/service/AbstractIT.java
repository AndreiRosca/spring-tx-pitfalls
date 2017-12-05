package com.endava.spring.tx.pitfalls.service;

import com.endava.spring.tx.pitfalls.config.AppConfiguration;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by anrosca on Dec, 2017
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfiguration.class })
public abstract class AbstractIT {
}
