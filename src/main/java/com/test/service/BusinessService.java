/**
 * 
 */
package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.model.Device;
import com.test.model.User;
import com.test.model.UserInput;
import com.test.model.UserProfile;
import com.test.repository.DataRepository;

/**
 * @author kpatrick
 *
 */
@Service
@Transactional
public class BusinessService {
	
	@Autowired
	private DataRepository repository;

	/**
	 * 
	 */
	public BusinessService() {
		super();
	}
	
	public UserProfile createNewProfile(UserProfile profile) {
		UserInput input = new UserInput();
		input.setId(profile.getUser().getId());
		input.setEmail(profile.getUser().getEmail());
		input.setCode("password");
		input.setFamily(profile.getUser().getFamily());
		input.setGiven(profile.getUser().getGiven());
		
		this.repository.createNewUser(input);
		this.repository.addUserServices(profile.serviceLinks());
		
		UserProfile saved = null;
		User newUser = this.repository.getUserById(input.getId());
		
		if(newUser != null) {
			saved = comepleteProfile(newUser);
		}
		
		return saved;
	}
	
	public UserProfile findUserProfile(String email) {
		UserProfile profile = null;
		
		User user = this.repository.findUser(email);
		
		if(user != null) {
			profile = comepleteProfile(user);
		}
		
		return profile;
	}
	
	public UserProfile getUserProfileById(String id) {
		UserProfile profile = null;
		
		User user = this.repository.getUserById(id);
		
		if(user != null) {
			profile = comepleteProfile(user);
		}
		
		return profile;
	}
	
	protected UserProfile comepleteProfile(User user) {
		
		UserProfile profile = null;
		
		if(user != null) {
			UserInput input = new UserInput();
			input.setId(user.getId());
			input.setEmail(user.getEmail());
			input.setGiven(user.getGiven());
			input.setFamily(user.getFamily());
			
			profile = new UserProfile(this.repository.retrieveServices());
			profile.setUser(user);
			
			List<com.test.model.Service> services = this.repository.retrieveUserServices(input);
			for(com.test.model.Service service : services) {
				profile.addService(service);;
			}
			
			List<Device> devices = this.repository.retrieveUserDevices(input);
			for(Device device : devices) {
				profile.saveDevice(device);
			}
		}
		
		return profile;
	}

}
