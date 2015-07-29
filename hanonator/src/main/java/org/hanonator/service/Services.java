package org.hanonator.service;

import static org.joox.JOOX.$;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


/**
 * All of the services
 * 
 * @author user104
 */
public final class Services {
	
	/**
	 * The system class loader
	 */
	private static final ClassLoader CLASS_LOADER = ClassLoader.getSystemClassLoader();

	/**
	 * The logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Services.class.getName());

	/**
	 * The collection of all services
	 */
	private static final Map<Class<? extends Service>, ? super Service> services = new HashMap<>();

	/**
	 * Private constructor so no instances can be made
	 */
	private Services() {
		
	}

	/**
	 * Registers a server to its containing class
	 * 
	 * @param service
	 */
	public static Service register(Service service) {
		return Services.register(service, service.getClass());
	}

	/**
	 * Registers a server to a given class
	 * 
	 * @param service
	 */
	public static Service register(Service service, Class<? extends Service> c) {
		services.put(c, service);
		return service;
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

	/**
	 * Reads from a file and will load all and start all of the services
	 * @param file
	 * @throws Exception
	 */
	public static List<Service> load(File file) throws Exception {
		List<Service> out = new ArrayList<>();
		$($(file).document()).find("service").forEach(e -> {
			try {
				/*
				 * Get the class name
				 */
				String class_name = e.getTextContent().trim();
				
				/*
				 * Get the service class
				 */
				Class<Service> c = Service.class;
				
				/*
				 * Get the class object
				 */
				Service service = c.cast(CLASS_LOADER.loadClass(class_name).newInstance());
				
				/*
				 * Register a new instance
				 */
				register(service);
				out.add(service);
				logger.info("Registering service " + class_name);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		});
		return out;
	}

	/**
	 * Stops all services immediately
	 */
	public static void shutdownNow() {
		for (Iterator<? super Service> iterator = services.values().iterator(); iterator.hasNext(); ) {
			((Service) iterator.next()).stop();
			iterator.remove();
		}
	}

}