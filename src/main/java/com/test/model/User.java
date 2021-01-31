/**
 * 
 */
package com.test.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author kpatrick
 *
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "uniqueid")
	private String id = null;

	
	@Column(name = "email")
	private String email = null;
	
	@Column(name = "given")
	private String given = null;
	
	@Column(name = "family")
	private String family = null;

	/**
	 * 
	 */
	public User() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGiven() {
		return given;
	}

	public void setGiven(String given) {
		this.given = given;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		
		out.append(this.getGiven());
		out.append(" ");
		out.append(this.getFamily());
		out.append(" [");
		out.append(this.getId());
		out.append(" (");
		out.append(this.getEmail());
		out.append(")]");
		
		return out.toString();
	}
}
