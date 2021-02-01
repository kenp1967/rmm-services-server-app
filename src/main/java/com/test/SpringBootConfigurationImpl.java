/**
 * 
 */
package com.test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
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

	@Component
	public class DBInitializer {
		
		@Autowired
		private JdbcTemplate template;
		
		public DBInitializer() {
			super();
		}
		
		@EventListener
		public void initialize(ContextRefreshedEvent evt) {
			String ddl = this.extractResourceAsText("fullinit.sql");
			this.template.execute(ddl);
		}
		
		protected String extractResourceAsText(String resource) {
			StringBuilder text = new StringBuilder();
			
			try (BufferedReader reader = new BufferedReader(new InputStreamReader(obtainResourceStream(resource)))) {
				String line = null;
				
				while((line = reader.readLine()) != null) {
					text.append(line);
					text.append("\n");
				}
			} catch (Throwable t) {
				throw new RuntimeException(t);
			}
			
			return text.toString();
		}
		
		protected InputStream obtainResourceStream(String resource) {
			InputStream stream = null;
			
			stream = this.getClass().getResourceAsStream(resource);
			
			if(stream == null) {
				stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
			}
			
			if(stream == null) {
				stream = ClassLoader.getSystemResourceAsStream(resource);
			}
			
			return stream;
		}
	}
}
