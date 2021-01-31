/**
 * 
 */
package com.test.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author kpatrick
 *
 */
@Entity
@Table(name = "services")
public class Service implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "key")
	private String id = null;

	
	@Column(name = "display")
	private String display = null;
	
	@Column(name = "cost")
	private BigDecimal cost = null;

	/**
	 * 
	 */
	public Service() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String email) {
		this.display = email;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder();
		
		out.append(this.getDisplay());
		out.append(" ");
		out.append(this.getCost());
		out.append(" [");
		out.append(this.getId());
		out.append("]");
		
		return out.toString();
	}
}
