/**
 * 
 */
package com.test.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author kpatrick
 *
 */
@Entity
@Table(name = "user_devices")
public class Device implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "uniqueid")
	private String id = null;

	@Column(name = "name")
	private String name = null;

	@Column(name = "device_type")
	private String type = null;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ownerid")
	private User owner = null;
	
	/**
	 * 
	 */
	public Device() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		
		out.append(this.getName());
		out.append(" ");
		out.append(this.getType());
		out.append(" [");
		out.append(this.getId());
		out.append("]");
		
		return out.toString();
	}
}
