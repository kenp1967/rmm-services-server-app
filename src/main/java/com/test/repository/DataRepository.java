/**
 * 
 */
package com.test.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.test.model.Device;
import com.test.model.Service;
import com.test.model.User;
import com.test.model.UserInput;

/**
 * @author kpatrick
 *
 */
@Repository
@Transactional
public class DataRepository implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EntityManager manager;
	@Autowired
	private JdbcTemplate template;

	/**
	 * 
	 */
	public DataRepository() {
		super();
	}
	
	
	public void createNewUser(UserInput input) {
		this.manager.persist(input.getDelegate());
		StringBuilder sql = new StringBuilder("UPDATE users SET code = '");
		sql.append(input.getCode());
		sql.append("' WHERE uniqueid ='");
		sql.append(input.getId());
		sql.append("'");
		
		this.template.execute(sql.toString());
	}

	
	public User findUser(String email) {
		StringBuilder query = new StringBuilder("SELECT uniqueid FROM users WHERE email = '");
		query.append(email);
		query.append("'");
		
		List<String> ids = this.template.queryForList(query.toString(), String.class);
		if(ids.isEmpty()) {
			throw new RuntimeException("Email not found.");
		} else {
			return this.getUserById(ids.get(0));
		}
	}
	
	
	public User getUserById(String id) {
		User user = this.manager.find(User.class, id);
		return user;
	}
	
	
	public void updateUser(UserInput input) {
		this.manager.merge(input.getDelegate());
		StringBuilder sql = new StringBuilder("UPDATE users SET code = '");
		sql.append(input.getCode());
		sql.append("' WHERE uniqueid ='");
		sql.append(input.getId());
		sql.append("'");
		
		this.template.execute(sql.toString());
	}

	
	public void deleteUser(User user) {
		User found = this.manager.find(User.class, user.getId());
		this.manager.remove(found);
	}
	
	public Map<String, Service> retrieveServices() {
		Map<String, Service> mapped = new HashMap<String, Service>();
		List<Service> services = this.manager.createQuery("SELECT s from Service s", Service.class).getResultList();
		for(Service service : services) {
			mapped.put(service.getId(), service);
		}
		
		return mapped;
	}

	public List<Service> retrieveUserServices(UserInput input) {
		Service service = null;
		List<Service> services = new ArrayList<Service>();
		List<String> serviceIds = this.template.queryForList("SELECT serviceid FROM user_services WHERE userid = ?;", String.class, input.getId());
		
		for(String id : serviceIds) {
			service = this.manager.find(Service.class, id);
			if(service != null) {
				services.add(service);
			}
		}
		
		return services;
	}

	public Integer addUserServices(Collection<Entry<String, String>> links) {
		Integer total = 0;
		
		// Key is always serviceid
		for(Entry<String, String> entry : links) {
			total += this.template.update("INSERT INTO user_services VALUES (?, ?)", entry.getKey(), entry.getValue());
		}
		
		return total;
	}

	public Integer removeUserServices(Collection<Entry<String, String>> links) {
		Integer total = 0;
		
		// Key is always serviceid
		for(Entry<String, String> entry : links) {
			total += this.template.update("INSERT INTO user_services VALUES (?, ?);", entry.getKey(), entry.getValue());
		}
		
		return total;
	}

	public List<Device> retrieveUserDevices(UserInput input) {
		StringBuilder sql = new StringBuilder("SELECT d FROM Device d WHERE ownerid = '");
		sql.append(input.getId());
		sql.append("'");
		
		List<Device> devices = this.manager.createQuery(sql.toString(), Device.class).getResultList();
		return devices;
	}
	
	public void addDevice(Device device) {
		this.manager.persist(device);
	}

	
	public void addDevices(List<Device> devices) {
		for(Device device : devices) {
			this.addDevice(device);
		}
	}

	
	public void updateDevice(Device device) {
		this.manager.persist(device);
	}

	
	public void updateDevices(List<Device> devices) {
		for(Device device : devices) {
			this.updateDevice(device);
		}
	}

	
	public void deleteDevice(Device device) {
		Device found = this.manager.find(Device.class, device.getId());
		this.manager.remove(found);
	}

	
	public void deleteDevices(List<Device> devices) {
		for(Device device : devices) {
			this.deleteDevice(device);
		}
	}

}
