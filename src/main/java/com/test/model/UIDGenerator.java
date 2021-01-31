/**
 * 
 */
package com.test.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.net.InetAddress;
import java.rmi.server.UID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kenneth.patrick
 *
 */
public class UIDGenerator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UIDGenerator.class.getSimpleName());
	
	private static InetAddress LOCAL_HOST = null;
	
	private static final UIDGenerator GENERATOR = new UIDGenerator();

	/**
	 * 
	 */
	public UIDGenerator() {
		super();
		this.initialize();
	}
	
	public static String nextUniqueId() {
		String uid = GENERATOR.createId(UIDGenerator.LOCAL_HOST.getHostAddress());
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("NEXT UID: {}", uid);
		}
		
		return uid;
	}
	
	protected void initialize() {
		try {
			UIDGenerator.LOCAL_HOST = InetAddress.getLocalHost();
			
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("This Machine: {} [{}]", UIDGenerator.LOCAL_HOST.getHostName(), UIDGenerator.LOCAL_HOST.getHostAddress());
			}
		} catch (Throwable t) {
			throw new RuntimeException(t);
		}
	}

	/**
	 * @param address
	 * @return
	 */
	protected String createId(String address) {
		String hexAddress = this.getHexAddress(address);
		String uid = new UID().toString().toUpperCase();

		StringBuilder buf = new StringBuilder();

		buf.append(hexAddress);
		buf.append(":");
		buf.append(uid);

		return buf.toString();
	}

	/**
	 * @param address
	 * @return
	 */
	protected String getHexAddress(String address) {
		StringBuilder hex = new StringBuilder();

		if (address != null) {
			String[] parts = address.split("\\.");
			BigInteger number = null;
			String hexPart = null;

			for (int i = 0; parts != null && i < parts.length; i++) {
				number = new BigInteger(parts[i]);
				hexPart = number.toString(16);

				if (hexPart.length() < 2) {
					hexPart = new StringBuilder("0").append(hexPart).toString();
				}

				hex.append(hexPart.toUpperCase());
			}
		}

		return hex.toString();
	}
}
