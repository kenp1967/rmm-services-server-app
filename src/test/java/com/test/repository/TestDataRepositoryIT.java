/**
 * 
 */
package com.test.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.test.TestingConfigurationImpl;
import com.test.model.Service;
import com.test.model.User;
import com.test.model.UserInput;


/**
 * @author kpatrick
 *
 */
@ExtendWith({ SpringExtension.class })
@WebAppConfiguration
@ContextConfiguration(classes = {TestingConfigurationImpl.class})
@Rollback(value = false)
public class TestDataRepositoryIT {

	private static final Random RANDOM = new Random();
	
	private static final List<User> USERS = new ArrayList<User>();
	
	@Autowired
	private DataRepository repository;
	
	@Autowired
	private JdbcTemplate template;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		USERS.clear();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
		if(USERS.isEmpty()) {
			List<String> ids = this.template.queryForList("SELECT uniqueid FROM users", String.class);
			
			for(String id : ids) {
				USERS.add(this.repository.getUserById(id));
			}
		}
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.test.repository.DataRepository#createNewUser(com.test.model.UserInput)}.
	 */
	@Test
	public void testCreateNewUser() {
		UserInput input = new UserInput();
		input.setCode("password");
		input.setEmail("new.user@nomail.org");
		input.setGiven("New");
		input.setFamily("User");
		
		assertNotNull(input.getId());
		
		this.repository.createNewUser(input);
		
		User user = this.repository.findUser(input.getEmail());
		
		assertNotNull(user);
		assertEquals(input.getId(), user.getId());
		
		USERS.add(user);
	}

	/**
	 * Test method for {@link com.test.repository.DataRepository#findUser(java.lang.String)}.
	 */
	@Test
	public void testFindUser() {
		User existing = USERS.get(RANDOM.nextInt(USERS.size()));
		String email = existing.getEmail();
		
		User user = this.repository.findUser(email);
		
		assertNotNull(user);
		assertEquals(email, user.getEmail());
	}

	/**
	 * Test method for {@link com.test.repository.DataRepository#getUserById(java.lang.String)}.
	 */
	@Test
	public void testGetUserById() {
		User existing = USERS.get(RANDOM.nextInt(USERS.size()));
		String id = existing.getId();
		
		User user = this.repository.getUserById(id);
		
		assertNotNull(user);
		assertEquals(id, user.getId());
	}

	/**
	 * Test method for {@link com.test.repository.DataRepository#updateUser(com.test.model.UserInput)}.
	 */
	@Test
	public void testUpdateUser() {
		User existing = USERS.get(RANDOM.nextInt(USERS.size()));
		
		UserInput input = new UserInput();
		input.setEmail(existing.getEmail());
		input.setGiven(existing.getGiven());
		input.setFamily(existing.getFamily().concat(", PLC"));
		
		this.repository.updateUser(input);
		User user = this.repository.getUserById(existing.getId());
		
		assertNotNull(user);
	}

	/**
	 * Test method for {@link com.test.repository.DataRepository#deleteUser(com.test.model.User)}.
	 */
	@Test
	public void testDeleteUser() {
		User existing = USERS.get(RANDOM.nextInt(USERS.size()));
		
		this.repository.deleteUser(existing);
		User user = this.repository.getUserById(existing.getId());
		
		assertNull(user);
		USERS.remove(existing);
	}


	/**
	 * Test method for {@link com.test.repository.DataRepository#retrieveServices()}.
	 */
	@Test
	public void testRetrieveServices() {
		Map<String, Service> services = this.repository.retrieveServices();
		assertNotNull(services);
		assertFalse(services.isEmpty());
	}

	/**
	 * Test method for {@link com.test.repository.DataRepository#retrieveUserServices(com.test.model.UserInput)}.
	 */
	@Test
	public void testRetrieveUserServices() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.test.repository.DataRepository#addUserServices(java.util.Collection)}.
	 */
	@Test
	public void testAddUserServices() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.test.repository.DataRepository#removeUserServices(java.util.Collection)}.
	 */
	@Test
	public void testRemoveUserServices() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.test.repository.DataRepository#updateService(com.test.model.Service)}.
	 */
	@Test
	public void testUpdateService() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.test.repository.DataRepository#deleteService(com.test.model.Service)}.
	 */
	@Test
	public void testDeleteService() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.test.repository.DataRepository#retrieveUserDevices(com.test.model.UserInput)}.
	 */
	@Test
	public void testRetrieveUserDevices() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.test.repository.DataRepository#addDevice(com.test.model.Device)}.
	 */
	@Test
	public void testAddDevice() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.test.repository.DataRepository#addDevices(java.util.List)}.
	 */
	@Test
	public void testAddDevices() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.test.repository.DataRepository#updateDevice(com.test.model.Device)}.
	 */
	@Test
	public void testUpdateDevice() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.test.repository.DataRepository#updateDevices(java.util.List)}.
	 */
	@Test
	public void testUpdateDevices() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.test.repository.DataRepository#deleteDevice(com.test.model.Device)}.
	 */
	@Test
	public void testDeleteDevice() {
		//fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.test.repository.DataRepository#deleteDevices(java.util.List)}.
	 */
	@Test
	public void testDeleteDevices() {
		//fail("Not yet implemented");
	}

}
