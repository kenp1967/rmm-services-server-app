package com.test.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.test.TestingConfigurationImpl;
import com.test.model.Device;
import com.test.model.DeviceTypes;
import com.test.model.Service;
import com.test.model.UIDGenerator;
import com.test.model.User;
import com.test.model.UserProfile;
import com.test.repository.DataRepository;

@ExtendWith({ SpringExtension.class })
@WebAppConfiguration
@ContextConfiguration(classes = {TestingConfigurationImpl.class})
@Rollback(value = false)
class BusinessServiceIT {
	
	private static Map<String, Service> SERVICES = null;
	
	@Autowired
	private BusinessService service;

	@Autowired
	private DataRepository repository;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		if(SERVICES == null) {
			SERVICES = this.repository.retrieveServices();
		}
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCreateNewProfile() {
		User user = new User();
		user.setId(UIDGenerator.nextUniqueId());
		user.setEmail("good.friend@noone.us");
		user.setGiven("Good");
		user.setFamily("Friend");
		
		UserProfile profile = new UserProfile();
		profile.setUser(user);
		profile.addService(SERVICES.get("CDB"));
		profile.addService(SERVICES.get("DVC"));
		profile.addService(SERVICES.get("TVW"));
		
		Device workstation = new Device();
		workstation.setId(UIDGenerator.nextUniqueId());
		workstation.setName("WinWorks 71");
		workstation.setType(DeviceTypes.WINWORKSTATION.toString());
		profile.saveDevice(workstation);
		profile.addService(SERVICES.get("AVW"));
		
		Device server = new Device();
		server.setId(UIDGenerator.nextUniqueId());
		server.setName("WinServ 71");
		server.setType(DeviceTypes.WINSERVER.toString());
		profile.saveDevice(server);
		profile.addService(SERVICES.get("AVM"));
		
		Device mac1 = new Device();
		mac1.setId(UIDGenerator.nextUniqueId());
		mac1.setName("Mac1 71");
		mac1.setType(DeviceTypes.MAC.toString());
		profile.saveDevice(mac1);
		
		Device mac2 = new Device();
		mac2.setId(UIDGenerator.nextUniqueId());
		mac2.setName("Mac2 71");
		mac2.setType(DeviceTypes.MAC.toString());
		profile.saveDevice(mac2);
		
		Device mac3 = new Device();
		mac3.setId(UIDGenerator.nextUniqueId());
		mac3.setName("Mac3 71");
		mac3.setType(DeviceTypes.MAC.toString());
		profile.saveDevice(mac3);
		
		assertEquals(71.0, profile.getTotalDollars().doubleValue());
		
		UserProfile created = this.service.createNewProfile(profile);
		
		assertEquals(71.0, created.getTotalDollars().doubleValue());
	}

	@Test
	void testFindProfileByEmail() {
		//From seed data
		String email = "tester3@fakemail.us";
		
		UserProfile profile = this.service.findUserProfile(email);
		
		assertNotNull(profile);
	}

	@Test
	void testGetProfileById() {
		//From seed data
		String id = "AC134343:18CF3BD3:1774F3025AD:-7FFC";
		
		UserProfile profile = this.service.findUserProfile(id);
		
		assertNotNull(profile);
	}

}
