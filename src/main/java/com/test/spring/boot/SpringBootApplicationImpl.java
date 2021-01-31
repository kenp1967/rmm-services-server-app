/**
 * 
 */
package com.test.spring.boot;

import java.io.Serializable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author kpatrick
 *
 */
@SpringBootApplication
@ComponentScan({"com.test", "org.jboss.narayana"})
@EntityScan(basePackages = {"com.test.model", "org.jboss.narayana"})
public class SpringBootApplicationImpl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public SpringBootApplicationImpl() {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String... args) {
		SpringApplication.run(SpringBootApplicationImpl.class, args);
	}

}
