package org.hanonator.service;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author user104
 *
 */
public final class Services {

	/**
	 * The collection of all services
	 */
	private static final Map<Class<? extends Service>, ? super Service> services = new HashMap<>();

	private Services() {
		
	}

	/**
	 * Registers a server to its containing class
	 * 
	 * @param service
	 */
	public static void register(Service service) {
		Services.register(service, service.getClass());
	}

	/**
	 * Registers a server to a given class
	 * 
	 * @param service
	 */
	public static void register(Service service, Class<? extends Service> c) {
		services.put(c, service);
	}

	/**
	 * Gets a service based on the class associated with it
	 * 
	 * @param c
	 * @return
	 */
	public static <T extends Service> T get(Class<T> c) {
		return c.cast(services.get(c));
	}

}