/**
 * 
 */
package com.test;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author kpatrick
 *
 */
@SpringBootConfiguration
@EnableTransactionManagement
@ComponentScan({"com.test", "org.jboss.narayana"})
@EntityScan(basePackages = {"com.test.model", "org.jboss.narayana"})
public class SpringBootConfigurationImpl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public SpringBootConfigurationImpl() {
		super();
	}
	/*
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("postgres");
		return factory;
	}
	
	@Bean
	public EntityManager entityManager(EntityManagerFactory factory) {
		EntityManager manager = factory.createEntityManager();
		return manager;
	}
	*/
}
