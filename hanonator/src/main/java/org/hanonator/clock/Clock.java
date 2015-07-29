package org.hanonator.clock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.hanonator.clock.ClockWorker.Result;
import org.hanonator.service.Service;

public final class Clock implements Service, Runnable {

	/**
	 * The rate at which the clock sends out tick updates
	 */
	private static final long CYCLE_RATE = 50L;
	
	/**
	 * The logger for this class
	 */
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
	public void start() {
		service.scheduleAtFixedRate(this, CYCLE_RATE, CYCLE_RATE, TimeUnit.MILLISECONDS);
	}

	@Override
	public void stop() {
		service.shutdownNow();
	}

	@Override
	public void run() {
		synchronized(workers) {
			for (Iterator<ClockWorker> iterator = workers.iterator(); iterator.hasNext(); ) {
				ClockWorker worker = iterator.next();
				try {
					/*
					 * Make the worker do his thing. If anything other than reschedule result is
					 * returned, remove it from the active workers
					 */
					if (worker.tick() != Result.RESCHEDULE) {
						iterator.remove();
					}
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

	/**
	 * Submit a worker to the collection of workers
	 * @param worker
	 */
	public void submit(ClockWorker worker) {
		synchronized (workers) {
			workers.add(worker);
		}
	}
	
	/**
	 * submit a single use runnable to be executed next cycle
	 * @param runnable
	 */
	public void submit(Runnable runnable) {
		this.submit(() -> {
			runnable.run();
			return Result.STOP;
		});
	}

}