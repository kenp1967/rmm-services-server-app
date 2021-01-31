/**
 * 
 */
package com.test.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author kpatrick
 *
 */
public class UserProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user = null;
	
	private Map<String, Service> allServices = null;

	private Map<String, Device> devices = new HashMap<String, Device>();
	
	private Map<String, Service> services = new HashMap<String, Service>();

	/**
	 * 
	 */
	public UserProfile() {
		super();
	}

	public UserProfile(Map<String, Service> svcs) {
		super();
		this.allServices = svcs;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Collection<Service> getAllServices() {
		return Collections.unmodifiableCollection(this.allServices.values());
	}

	public Collection<Device> getDevices() {
		return Collections.unmodifiableCollection(this.devices.values());
	}

	public void saveDevice(Device device) {
		this.devices.put(device.getId(), device);
	}

	public void removeDevice(Device device) {
		this.devices.remove(device.getId());
	}
	
	public Collection<Service> getUserServices() {
		return Collections.unmodifiableCollection(this.services.values());
	}

	public void addService(Service service) {
		this.services.put(service.getId(), service);
	}

	public void removeService(Service service) {
		this.services.remove(service.getId());
	}
	
	public BigDecimal getTotalDollars () {
		BigDecimal total = BigDecimal.ZERO;
		
		BigDecimal winAV = BigDecimal.ZERO;
		BigDecimal macAV = BigDecimal.ZERO;
		BigDecimal perDevice = BigDecimal.ZERO;
		
		for(Service service : this.services.values()) {
			switch (service.getId()) {
			case "AVW":
				winAV = winAV.add(service.getCost());
				break;
			case "AVM":
				macAV = macAV.add(service.getCost());
				break;
			default:
				perDevice = perDevice.add(service.getCost());
				break;
			}
		}
		
		total = perDevice.multiply(BigDecimal.valueOf(this.devices.size()));
		
		for(Device device : this.devices.values()) {
			if(device.getType().toUpperCase().contains("WINDOWS")) {
				total = total.add(winAV);
			} else {
				total = total.add(macAV);
			}
		}
		
		return total;
	}
	
	public Collection<Device> deviceInput() {
		return Collections.unmodifiableCollection(this.devices.values());
	}

	public Collection<Entry<String, String>> serviceLinks() {

		List<Entry<String, String>> svcs = new ArrayList<Entry<String,String>>();
		
		for(String key : this.services.keySet()) {
			svcs.add(new SimpleImmutableEntry<String, String>(key, this.getUser().getId()));
		}
		
		return svcs;
	}
}
