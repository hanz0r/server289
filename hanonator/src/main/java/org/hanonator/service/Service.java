package org.hanonator.service;

public interface Service {

	/**
	 * Starts the service. Called upon startup
	 * 
	 * @throws ServiceException
	 */
	public abstract void start();

	/**
	 * Stops the service. Called upon application shutdown and when the
	 * service is stopped manually
	 * 
	 * @throws ServiceException
	 */
	public abstract void stop();

}