package com.socialone.connection;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.socialone.spring.configuration.BusinessTierConfigurations;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BusinessTierConfigurations.class)
@ActiveProfiles(value = "test")
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
abstract public class AbstractBusinessTierTest {

}
