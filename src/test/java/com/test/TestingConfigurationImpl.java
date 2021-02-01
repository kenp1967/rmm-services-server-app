/**
 * 
 */
package com.test;

import java.io.Serializable;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author kpatrick
 *
 */
@ContextConfiguration(classes = {TestingConfigurationImpl.class})
@PropertySource(value = { "classpath:test.properties" })
@ComponentScan({"com.test", "org.jboss.narayana"})
@EntityScan(basePackages = {"com.test.model", "org.jboss.narayana"})
public class TestingConfigurationImpl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public TestingConfigurationImpl() {
		super();
	}

/*
@Bean
public EntityManagerFactory entityManagerFactory() {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("testingdb");
	return factory;
}

@Bean
public EntityManager entityManager(EntityManagerFactory factory) {
	EntityManager manager = factory.createEntityManager();
	return manager;
}
*/
}
