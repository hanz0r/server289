package org.hanonator.clock;

/**
 * 
 * @author user104
 *
 */
public interface ClockWorker {

	/**
	 * 
	 * @throws Exception
	 */
	public abstract void tick() throws ClockException;
	
	/**
	 * Handle the exceptions thrown by the clock
	 * 
	 * @param clock
	 * @param exception
	 */
	default void handleException(Clock clock, ClockException exception) {
		exception.printStackTrace();
	}

	/**
	 * 
	 * @author user104
	 */
	public static enum Result {
		/**
		 * Keep the clockworker in the schedule
		 */
		RESCHEDULE,
		
		/**
		 * Prevent this task from being executed again
		 */
		CANCEL,
		
		/**
		 * Abort all running tasks and shut down the clock service
		 */
		ABORT;
	}

}