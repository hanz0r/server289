package org.hanonator.clock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.hanonator.service.Service;
import org.hanonator.service.ServiceException;

public final class Clock implements Service, Runnable {

	/**
	 * The rate at which the clock sends out tick updates
	 */
	private static final long CYCLE_RATE = 50L;
	
	private static final Logger logger = Logger.getLogger(Clock.class.getName());
	
	/**
	 * The collection of workers
	 */
	private final List<ClockWorker> workers = new ArrayList<>();
	
	/**
	 * The scheduled executorservice responsible for the clock updates
	 */
	private final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();

	@Override
	public void start() throws ServiceException {
		service.scheduleAtFixedRate(this, CYCLE_RATE, CYCLE_RATE, TimeUnit.MILLISECONDS);
	}

	@Override
	public void stop() throws ServiceException {
		service.shutdown();
	}

	@Override
	public void run() {
		for (Iterator<ClockWorker> iterator = workers.iterator(); iterator.hasNext(); ) {
			ClockWorker worker = iterator.next();
			
			try {
			
				worker.tick();
			} catch (ClockException e) {
				/*
				 * Handle the exception higher up the stack
				 */
				worker.handleException(this, e);
				
				/*
				 * Remove the clock worker to prevent further errors
				 */
				iterator.remove();
				
				/*
				 * output
				 */
				logger.info("deregistered clockworker " + worker);
			}
		}
	}

}