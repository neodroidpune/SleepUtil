package com.neodroidpune.sleeputil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * public class Sleeper
 * <br>
 * To sleep for defined milliseconds
 * <br>
 * @param long milli - Time in millisecond to sleep
 * @param {@link SleepListner} callBack - Callback after completing sleep
 * @param int id - Unique id
 * @author P Ravikant
 * @version 1.0
 * */

public class Sleeper {
	
	private Boolean isRunning;
	private SleepListner callBack;
	private long milli;
	private Timer timer;
	private int id;

	
	/**
	 * Default Constructor
	 * <br>
	 * @param long milli - Time in millisecond to sleep
	 * @param {@link SleepListner} callBack - Callback after completing sleep
	 * @param int id - Unique id
	 * */
	public Sleeper(long milli, SleepListner callBack, int id) {
		this.milli = milli;
		this.callBack = callBack;
		this.id = id;
	}

	/**
	 * Start sleeper
	 * */
	public void start() {
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				callBack.onSleepComplete(id);
			}
		}, milli);
		isRunning = true;
	}

	/**
	 * Restart sleeper
	 * */
	public void restartTimer() {
		stop();
		start();
	}

	/**
	 * Stop sleeper
	 * */
	public void stop() {
		timer.cancel();
		isRunning = false;
	}

	/**
	 * Check if Sleeper is running
	 * 
	 * @return boolean isTimerRunning
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * public interface SleepListner
	 * <br><br>
	 * Callback to Sleeper
	 * <br>
	 * @author P Ravikant
	 * @version 1.0
	 * */
	public interface SleepListner {

		/**
		 * public void SleepListner
		 * <br><br>
		 * Callback comes when Sleeper completes
		 * <br>
		 * @param
		 * */
		public void onSleepComplete(int id);
	}
}