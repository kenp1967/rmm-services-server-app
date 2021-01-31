/**
 * 
 */
package com.test.model;

import java.io.Serializable;

/**
 * @author kpatrick
 *
 */
public class UserInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code = null;
	
	private User delegate = new User();

	/**
	 * 
	 */
	public UserInput() {
		super();
		this.delegate.setId(UIDGenerator.nextUniqueId());
	}
	
	public User getDelegate() {
		return this.delegate;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getId() {
		return delegate.getId();
	}

	public void setId(String id) {
		delegate.setId(id);
	}

	public String getEmail() {
		return delegate.getEmail();
	}

	public void setEmail(String email) {
		delegate.setEmail(email);
	}

	public String getGiven() {
		return delegate.getGiven();
	}

	public void setGiven(String given) {
		delegate.setGiven(given);
	}

	public String getFamily() {
		return delegate.getFamily();
	}

	public void setFamily(String family) {
		delegate.setFamily(family);
	}

	public String toString() {
		return delegate.toString();
	}

}
