package com.test.model;

public enum DeviceTypes {
	WINWORKSTATION("Windows Workstation"), 
	WINSERVER("Windows Server"), 
	MAC("Mac");
	
	private String display;
	
	private DeviceTypes(String display) {
		this.display = display;
	}

	public String getDisplay() {
		return this.display;
	}
	
	@Override
	public String toString() {
		return this.getDisplay();
	}
}
