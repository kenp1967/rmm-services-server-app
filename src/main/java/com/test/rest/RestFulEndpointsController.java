/**
 * 
 */
package com.test.rest;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.Service;
import com.test.model.UserProfile;
import com.test.repository.DataRepository;
import com.test.service.BusinessService;

/**
 * @author kpatrick
 *
 */
@RestController
@RequestMapping("/rest")
public class RestFulEndpointsController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private DataRepository repository;
	
	@Autowired
	private BusinessService service;
	
	/**
	 * 
	 */
	public RestFulEndpointsController() {
		super();
	}
	
	@GetMapping("/heartbeat")
	public ResponseEntity<HeartBeat> respond() {
		ResponseEntity<HeartBeat> beat = new ResponseEntity<HeartBeat>(new HeartBeat(), HttpStatus.OK);
		return beat;
	}
	
	@GetMapping("/services")
	public ResponseEntity<Collection<Service>> showServices() {
		Collection<Service> services = this.repository.retrieveServices().values();
		ResponseEntity<Collection<Service>> serviceCollection = new ResponseEntity<Collection<Service>>(services, HttpStatus.OK);
		return serviceCollection;
	}
	
	@PutMapping("/profile/create")
	public ResponseEntity<UserProfile> createProfile(UserProfile input) {
		UserProfile profile = this.service.createNewProfile(input);
		
		ResponseEntity<UserProfile> profileResponse = new ResponseEntity<UserProfile>(profile, HttpStatus.OK);
		return profileResponse;
	}
	
	@PostMapping("/profile/update")
	public ResponseEntity<UserProfile> updateProfile(UserProfile input) {
		UserProfile profile = this.service.createNewProfile(input);
		
		ResponseEntity<UserProfile> profileResponse = new ResponseEntity<UserProfile>(profile, HttpStatus.OK);
		return profileResponse;
	}
	
	@GetMapping("/profile/find/{email}")
	public ResponseEntity<UserProfile> retrieveProfileByEmail(@PathVariable("email") String email) {
		UserProfile profile = this.service.findUserProfile(email);
		
		ResponseEntity<UserProfile> profileResponse = new ResponseEntity<UserProfile>(profile, HttpStatus.OK);
		return profileResponse;
	}
	
	@GetMapping("/profile/get/{id}")
	public ResponseEntity<UserProfile> retrieveProfileById(@PathVariable("id") String id) {
		UserProfile profile = this.service.getUserProfileById(id);
		
		ResponseEntity<UserProfile> profileResponse = new ResponseEntity<UserProfile>(profile, HttpStatus.OK);
		return profileResponse;
	}
	
	@DeleteMapping("/profile/id/delete/{id}")
	public ResponseEntity<Void> deleteProfileById(@PathVariable("id") String id) {
		this.service.deleteUserProfileById(id);
		
		ResponseEntity<Void> deleteResponse = new ResponseEntity<Void>(HttpStatus.OK);
		return deleteResponse;
	}
	
	@DeleteMapping("/profile/email/delete/{email}")
	public ResponseEntity<Void> deleteProfileByEmail(@PathVariable("email") String email) {
		this.service.deleteUserProfileById(email);
		
		ResponseEntity<Void> deleteResponse = new ResponseEntity<Void>(HttpStatus.OK);
		return deleteResponse;
	}
	
	protected static class HeartBeat {
		
		private Long time = new Date().getTime();
		private String status = "RUNNING";
		
		public HeartBeat() {
			super();
		}

		public Long getTime() {
			return time;
		}

		public String getStatus() {
			return status;
		}
	}
}
